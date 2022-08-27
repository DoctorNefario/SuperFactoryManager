package ca.teamdman.sfml.ast;

import ca.teamdman.sfm.common.program.ResourceType;

import java.util.ArrayList;
import java.util.List;

public record ResourceComparer<STACK, CAP>(
        ComparisonOperator op,
        Quantity num,
        ResourceIdentifier<STACK, CAP> res
) implements ASTNode {
    public BoolExpr toBooleanExpression(SetOperator setOp, LabelAccess labelAccess) {
        ResourceType<STACK, CAP> type = res.getType();
        return new BoolExpr(context -> {
            // get the inventories to check

            var handlers = type.getCaps(context, labelAccess);

            // track how many items seen
            var overallCount = 0;
            // track how many inventories satisfied the condition
            List<Boolean> satisfiedSet = new ArrayList<>();

            for (var cap : (Iterable<CAP>) handlers::iterator) {
                var invCount = 0;
                for (var stack : (Iterable<STACK>) type.collect(cap, labelAccess)::iterator) {
                    if (this.res.test(stack)) {
                        invCount += type.getCount(stack);
                        overallCount += type.getCount(stack);
                    }
                }
                satisfiedSet.add(this.op.test(invCount, this.num.value()));
            }

            var isOverallSatisfied = this.op.test(overallCount, this.num.value());
            return setOp.test(isOverallSatisfied, satisfiedSet);
        });
    }

}