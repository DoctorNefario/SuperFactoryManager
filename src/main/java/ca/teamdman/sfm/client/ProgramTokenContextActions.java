package ca.teamdman.sfm.client;

import ca.teamdman.sfm.SFM;
import ca.teamdman.sfm.common.net.ServerboundInputInspectionRequestPacket;
import ca.teamdman.sfm.common.net.ServerboundLabelInspectionRequestPacket;
import ca.teamdman.sfm.common.net.ServerboundOutputInspectionRequestPacket;
import ca.teamdman.sfm.common.registry.SFMPackets;
import ca.teamdman.sfml.SFMLLexer;
import ca.teamdman.sfml.SFMLParser;
import ca.teamdman.sfml.ast.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProgramTokenContextActions {

    public static Optional<Runnable> getContextAction(String programString, int cursorPosition) {
        var lexer = new SFMLLexer(CharStreams.fromString(programString));
        var tokens = new CommonTokenStream(lexer);
        var parser = new SFMLParser(tokens);
        var builder = new ASTBuilder();
        try {
            builder.visitProgram(parser.program());
            SFM.LOGGER.info("Gathering context actions for cursor position " + cursorPosition);
            return Stream.concat(
                            builder
                                    .getNodesUnderCursor(cursorPosition)
                                    .stream(),
                            builder
                                    .getNodesUnderCursor(cursorPosition - 1)
                                    .stream()
                    )
                    .map(pair -> getContextAction(programString, builder, pair.a, pair.b, cursorPosition))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();
        } catch (Throwable t) {
            return Optional.of(() -> ClientStuff.showProgramEditScreen("-- Encountered error, program parse failed:\n--"
                                                                       + t.getMessage(), next -> {
            }));
        }
    }

    public static Optional<Runnable> getContextAction(
            String programString,
            ASTBuilder builder,
            ASTNode node,
            ParserRuleContext parserRuleContext,
            int cursorPosition
    ) {
        SFM.LOGGER.info("Checking if context action exists for node {} {}", node.getClass(), node);
        if (node instanceof ResourceIdentifier<?, ?, ?> rid) {
            SFM.LOGGER.info("Found context action for resource identifier node");
            return Optional.of(() -> {
                String expansion = rid
                        .expand()
                        .stream()
                        .map(ResourceIdentifier::toStringCondensed)
                        .collect(Collectors.joining(",\n"));
                ClientStuff.showProgramEditScreen(expansion, next -> {
                });
            });
        } else if (node instanceof Label label) {
            SFM.LOGGER.info("Found context action for label node");
            return Optional.of(() -> SFMPackets.INSPECTION_CHANNEL.sendToServer(new ServerboundLabelInspectionRequestPacket(
                    label.name()
            )));
        } else if (node instanceof InputStatement) {
            if (cursorPosition > parserRuleContext.getStart().getStartIndex() + "INPUT".length()) {
                SFM.LOGGER.info("Found context action for input node, but the cursor isn't at the start of the node");
                return Optional.empty();
            }
            SFM.LOGGER.info("Found context action for input node");
            int nodeIndex = builder.getIndexForNode(node);
            return Optional.of(() -> SFMPackets.INSPECTION_CHANNEL.sendToServer(new ServerboundInputInspectionRequestPacket(
                    programString,
                    nodeIndex
            )));
        } else if (node instanceof OutputStatement) {
            if (cursorPosition > parserRuleContext.getStart().getStartIndex() + "OUTPUT".length()) {
                SFM.LOGGER.info("Found context action for output node, but the cursor isn't at the start of the node");
                return Optional.empty();
            }
            SFM.LOGGER.info("Found context action for output node");
            int nodeIndex = builder.getIndexForNode(node);
            return Optional.of(() -> SFMPackets.INSPECTION_CHANNEL.sendToServer(new ServerboundOutputInspectionRequestPacket(
                    programString,
                    nodeIndex
            )));
        }
        return Optional.empty();
    }

    public static boolean hasContextAction(Token token) {
        return switch (token.getType()) {
            case SFMLLexer.INPUT, SFMLLexer.OUTPUT, SFMLLexer.IDENTIFIER -> true;
            default -> false;
        };
    }
}