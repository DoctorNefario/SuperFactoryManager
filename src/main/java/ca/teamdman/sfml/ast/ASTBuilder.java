package ca.teamdman.sfml.ast;

import ca.teamdman.sfml.SFMLBaseVisitor;
import ca.teamdman.sfml.SFMLParser;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ASTBuilder extends SFMLBaseVisitor<ASTNode> {
    private final Set<Label>                       USED_LABELS    = new HashSet<>();
    private final Set<ResourceIdentifier<?, ?, ?>> USED_RESOURCES = new HashSet<>();


    @Override
    public StringHolder visitName(@Nullable SFMLParser.NameContext ctx) {
        if (ctx == null) return new StringHolder("");
        return visitString(ctx.string());
    }

    @Override
    public ASTNode visitResource(SFMLParser.ResourceContext ctx) {
        var str = ctx
                .IDENTIFIER()
                .stream()
                .map(ParseTree::getText)
                .collect(Collectors.joining(":"))
                .replaceAll("\\*", ".*");
        var rtn = ResourceIdentifier.fromString(str);
        USED_RESOURCES.add(rtn);
        rtn.assertValid();
        return rtn;
    }

    @Override
    public ResourceIdentifier<?, ?, ?> visitStringResource(SFMLParser.StringResourceContext ctx) {
        var rtn = ResourceIdentifier.fromString(visitString(ctx.string()).value());
        USED_RESOURCES.add(rtn);
        rtn.assertValid();
        return rtn;
    }

    @Override
    public StringHolder visitString(SFMLParser.StringContext ctx) {
        var content = ctx.getText();
        return new StringHolder(content.substring(1, content.length() - 1));
    }

    @Override
    public Label visitRawLabel(SFMLParser.RawLabelContext ctx) {
        var label = new Label(ctx.getText());
        USED_LABELS.add(label);
        return label;
    }

    @Override
    public Label visitStringLabel(SFMLParser.StringLabelContext ctx) {
        var label = new Label(visitString(ctx.string()).value());
        USED_LABELS.add(label);
        return label;
    }

    @Override
    public Program visitProgram(SFMLParser.ProgramContext ctx) {
        var name = visitName(ctx.name());
        var triggers = ctx
                .trigger()
                .stream()
                .map(this::visit)
                .map(Trigger.class::cast)
                .collect(Collectors.toList());
        var labels = USED_LABELS
                .stream()
                .map(Label::name)
                .collect(Collectors.toSet());
        return new Program(name.value(), triggers, labels, USED_RESOURCES);
    }

    @Override
    public ASTNode visitTimerTrigger(SFMLParser.TimerTriggerContext ctx) {
        var time = (Interval) visit(ctx.interval());
        if (time.getSeconds() < 1) throw new IllegalArgumentException("Minimum trigger interval is 1 second.");
        var block = visitBlock(ctx.block());
        return new TimerTrigger(time, block);
    }

    @Override
    public ASTNode visitBooleanRedstone(SFMLParser.BooleanRedstoneContext ctx) {
        ComparisonOperator comp = ComparisonOperator.GREATER_OR_EQUAL;
        Quantity           num  = new Quantity(0);
        if (ctx.comparisonOp() != null && ctx.number() != null) {
            comp = visitComparisonOp(ctx.comparisonOp());
            num  = visitNumber(ctx.number());
        }

        ComparisonOperator finalComp = comp;
        assert num.value() <= Integer.MAX_VALUE;
        int finalNum = (int) num.value();
        //noinspection DataFlowIssue // if the program is ticking, level shouldn't be null
        return new BoolExpr(
                programContext -> finalComp.test(
                        (long) programContext
                                .getManager()
                                .getLevel()
                                .getBestNeighborSignal(programContext
                                                               .getManager()
                                                               .getBlockPos()),
                        (long) finalNum
                )
        );
    }

    @Override
    public ASTNode visitPulseTrigger(SFMLParser.PulseTriggerContext ctx) {
        var block = visitBlock(ctx.block());
        return new RedstoneTrigger(block);
    }

    @Override
    public Quantity visitNumber(SFMLParser.NumberContext ctx) {
        return new Quantity(Long.parseLong(ctx.getText()));
    }

    @Override
    public Interval visitTicks(SFMLParser.TicksContext ctx) {
        var num = visitNumber(ctx.number());
        assert num.value() <= Integer.MAX_VALUE;
        return Interval.fromTicks((int) num.value());
    }

    @Override
    public Interval visitSeconds(SFMLParser.SecondsContext ctx) {
        var num = visitNumber(ctx.number());
        assert num.value() <= Integer.MAX_VALUE;
        return Interval.fromSeconds((int) num.value());
    }

    @Override
    public InputStatement visitInputStatementStatement(SFMLParser.InputStatementStatementContext ctx) {
        return (InputStatement) visit(ctx.inputstatement());
    }

    @Override
    public OutputStatement visitOutputStatementStatement(SFMLParser.OutputStatementStatementContext ctx) {
        return (OutputStatement) visit(ctx.outputstatement());
    }

    @Override
    public InputStatement visitInputstatement(SFMLParser.InputstatementContext ctx) {
        var labelAccess = visitLabelaccess(ctx.labelaccess());
        var matchers    = visitInputmatchers(ctx.inputmatchers());
        var each        = ctx.EACH() != null;
        return new InputStatement(labelAccess, matchers, each);
    }

    @Override
    public OutputStatement visitOutputstatement(SFMLParser.OutputstatementContext ctx) {
        var labelAccess = visitLabelaccess(ctx.labelaccess());
        var matchers    = visitOutputmatchers(ctx.outputmatchers());
        var each        = ctx.EACH() != null;
        return new OutputStatement(labelAccess, matchers, each);
    }

    @Override
    public LabelAccess visitLabelaccess(SFMLParser.LabelaccessContext ctx) {
        return new LabelAccess(
                ctx.label().stream().map(this::visit).map(Label.class::cast).collect(Collectors.toList()),
                visitSidequalifier(ctx.sidequalifier()),
                visitSlotqualifier(ctx.slotqualifier())
        );
    }

    @Override
    public IfStatement visitIfstatement(SFMLParser.IfstatementContext ctx) {
        var expressions = ctx
                .boolexpr()
                .stream()
                .map(this::visit)
                .map(BoolExpr.class::cast)
                .collect(Collectors.toList());
        var blocks = ctx.block().stream().map(this::visitBlock).collect(Collectors.toList());
        return new IfStatement(expressions, blocks);
    }

    @Override
    public IfStatement visitIfStatementStatement(SFMLParser.IfStatementStatementContext ctx) {
        return visitIfstatement(ctx.ifstatement());
    }

    @Override
    public BoolExpr visitBooleanTrue(SFMLParser.BooleanTrueContext ctx) {
        return new BoolExpr(__ -> true);
    }

    @Override
    public BoolExpr visitBooleanHas(SFMLParser.BooleanHasContext ctx) {
        var setOp       = visitSetOp(ctx.setOp());
        var labelAccess = visitLabelaccess(ctx.labelaccess());
        var comparison  = visitResourcecomparison(ctx.resourcecomparison());
        return comparison.toBooleanExpression(setOp, labelAccess);
    }

    @Override
    public SetOperator visitSetOp(@Nullable SFMLParser.SetOpContext ctx) {
        if (ctx == null) return SetOperator.OVERALL;
        return SetOperator.from(ctx.getText());
    }

    @Override
    public ResourceComparer<?, ?, ?> visitResourcecomparison(SFMLParser.ResourcecomparisonContext ctx) {
        var op = visitComparisonOp(ctx.comparisonOp());
        var num = visitNumber(ctx.number());
        var item = (ResourceIdentifier<?, ?, ?>) visit(ctx.resourceid());
        return new ResourceComparer<>(op, num, item);
    }

    @Override
    public ComparisonOperator visitComparisonOp(SFMLParser.ComparisonOpContext ctx) {
        return ComparisonOperator.from(ctx.getText());
    }

    @Override
    public BoolExpr visitBooleanConjunction(SFMLParser.BooleanConjunctionContext ctx) {
        var left  = (BoolExpr) visit(ctx.boolexpr(0));
        var right = (BoolExpr) visit(ctx.boolexpr(1));
        return new BoolExpr(left.and(right));
    }

    @Override
    public BoolExpr visitBooleanDisjunction(SFMLParser.BooleanDisjunctionContext ctx) {
        var left  = (BoolExpr) visit(ctx.boolexpr(0));
        var right = (BoolExpr) visit(ctx.boolexpr(1));
        return new BoolExpr(left.or(right));
    }

    @Override
    public BoolExpr visitBooleanFalse(SFMLParser.BooleanFalseContext ctx) {
        return new BoolExpr(__ -> false);
    }

    @Override
    public BoolExpr visitBooleanParen(SFMLParser.BooleanParenContext ctx) {
        return (BoolExpr) visit(ctx.boolexpr());
    }

    @Override
    public BoolExpr visitBooleanNegation(SFMLParser.BooleanNegationContext ctx) {
        var x = (BoolExpr) visit(ctx.boolexpr());
        return new BoolExpr(x.negate());
    }

    @Override
    public Limit visitQuantityRetentionLimit(SFMLParser.QuantityRetentionLimitContext ctx) {
        var quantity = visitQuantity(ctx.quantity());
        var retain   = visitRetention(ctx.retention());
        return new Limit(quantity.value(), retain.value());
    }

    @Override
    public ResourceLimits visitInputmatchers(@Nullable SFMLParser.InputmatchersContext ctx) {
        if (ctx == null) return new ResourceLimits(List.of(new ResourceLimit<>(
                new Limit(Long.MAX_VALUE, 0),
                ResourceIdentifier.MATCH_ALL
        )));
        return ((ResourceLimits) visit(ctx.movement())).withDefaults(Long.MAX_VALUE, 0);
    }


    @Override
    public ResourceLimits visitOutputmatchers(@Nullable SFMLParser.OutputmatchersContext ctx) {
        if (ctx == null)
            return new ResourceLimits(List.of(new ResourceLimit<>(
                    new Limit(Long.MAX_VALUE, Long.MAX_VALUE),
                    ResourceIdentifier.MATCH_ALL
            )));
        return ((ResourceLimits) visit(ctx.movement())).withDefaults(Long.MAX_VALUE, Long.MAX_VALUE);
    }

    @Override
    public ASTNode visitResourceLimitMovement(SFMLParser.ResourceLimitMovementContext ctx) {

        return new ResourceLimits(ctx.resourcelimit().stream()
                                          .map(this::visitResourcelimit)
                                          .collect(Collectors.toList()));
    }

    @Override
    public ResourceLimits visitLimitMovement(SFMLParser.LimitMovementContext ctx) {
        return new ResourceLimits(List.of(new ResourceLimit<>(
                (Limit) this.visit(ctx.limit()),
                ResourceIdentifier.MATCH_ALL
        )));
    }

    @Override
    public ResourceLimit<?, ?, ?> visitResourcelimit(SFMLParser.ResourcelimitContext ctx) {

        var res = (ResourceIdentifier<?, ?, ?>) visit(ctx.resourceid());

        if (ctx.limit() == null)
            return new ResourceLimit<>(res);

        var limit = (Limit) visit(ctx.limit());
        return new ResourceLimit<>(limit, res);
    }

    @Override
    public NumberRangeSet visitSlotqualifier(@Nullable SFMLParser.SlotqualifierContext ctx) {
        return visitRangeset(ctx == null ? null : ctx.rangeset());
    }

    @Override
    public NumberRangeSet visitRangeset(@Nullable SFMLParser.RangesetContext ctx) {
        if (ctx == null) return new NumberRangeSet(new NumberRange[]{new NumberRange(Long.MIN_VALUE, Long.MAX_VALUE)});
        return new NumberRangeSet(ctx.range().stream().map(this::visitRange).toArray(NumberRange[]::new));
    }

    @Override
    public NumberRange visitRange(SFMLParser.RangeContext ctx) {
        var iter  = ctx.number().stream().map(this::visitNumber).mapToLong(Quantity::value).iterator();
        var start = iter.next();
        if (iter.hasNext()) {
            var end = iter.next();
            return new NumberRange(start, end);
        } else {
            return new NumberRange(start, start);
        }
    }


    @Override
    public Limit visitRetentionLimit(SFMLParser.RetentionLimitContext ctx) {
        var retain = visitRetention(ctx.retention());
        return new Limit(-1, retain.value());
    }

    @Override
    public Limit visitQuantityLimit(SFMLParser.QuantityLimitContext ctx) {
        var quantity = visitQuantity(ctx.quantity());
        return new Limit(quantity.value(), -1);
    }

    @Override
    public Quantity visitRetention(@Nullable SFMLParser.RetentionContext ctx) {
        if (ctx == null) return new Quantity(-1);
        return visitNumber(ctx.number());
    }

    @Override
    public Quantity visitQuantity(@Nullable SFMLParser.QuantityContext ctx) {
        if (ctx == null) return new Quantity(Long.MAX_VALUE);
        return visitNumber(ctx.number());
    }

    @Override
    public DirectionQualifier visitSidequalifier(@Nullable SFMLParser.SidequalifierContext ctx) {
        if (ctx == null) return new DirectionQualifier(Stream.empty());
        var sides = ctx.side().stream().map(this::visitSide);
        return new DirectionQualifier(sides);
    }

    @Override
    public Side visitSide(SFMLParser.SideContext ctx) {
        return Side.valueOf(ctx.getText().toUpperCase(Locale.ROOT));
    }

    @Override
    public Block visitBlock(@Nullable SFMLParser.BlockContext ctx) {
        if (ctx == null) return new Block(Collections.emptyList());
        var statements = ctx
                .statement()
                .stream()
                .map(this::visit)
                .map(Statement.class::cast)
                .collect(Collectors.toList());
        return new Block(statements);
    }
}
