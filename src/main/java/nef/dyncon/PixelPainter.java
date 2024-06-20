package nef.dyncon;

import nef.dyncon.pixel.GridPos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class PixelPainter extends JFrame {
    private static final int PIXEL_SIZE = 20; // Size of each pixel in pixels
    private static final int GRID_WIDTH = 40; // Number of pixels in each row
    private static final int GRID_HEIGHT = 30; // Number of pixels in each column

    private final JPanel drawPanel;
    private final boolean[][] pixels = new boolean[GRID_HEIGHT][GRID_WIDTH];
    private final NodeGrouper nodeGrouper = new NodeGrouper() {
        @Override
        protected long[] getNeighbors(long node) {
            int x = (int) (node >> 32);
            int y = (int) node;

            return new long[]{
                    GridPos.asLong(x, y - 1),
                    GridPos.asLong(x, y + 1),
                    GridPos.asLong(x - 1, y),
                    GridPos.asLong(x + 1, y),
//                    GridPos.asLong(x - 1, y - 1),
//                    GridPos.asLong(x - 1, y + 1),
//                    GridPos.asLong(x + 1, y - 1),
//                    GridPos.asLong(x + 1, y + 1),
            };
        }
    };

    private final HashMap<Integer, Color> colorMap = new HashMap<>();

    public PixelPainter() {
        setTitle("Pixel Painter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(PIXEL_SIZE * GRID_WIDTH, PIXEL_SIZE * GRID_HEIGHT);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                for (int y = 0; y < GRID_HEIGHT; y++) {
                    for (int x = 0; x < GRID_WIDTH; x++) {
                        if (pixels[y][x]) {
                            g.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                        }
                    }
                }
                for (int i = 0; i < nodeGrouper.groups.size(); i++) {
                    var group = nodeGrouper.groups.get(i);
                    var color = colorMap.computeIfAbsent(i, _a -> Color.getHSBColor(
                            (float) Math.random(),
                            (float) Math.random() * 0.7f + 0.3f,
                            (float) Math.random() * 0.7f + 0.3f));
                    g.setColor(color);
                    for (long pos : group) {
                        int x = (int) (pos >> 32);
                        int y = (int) pos;
                        g.fillRect(x * PIXEL_SIZE, y * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
                    }
                }
            }
        };

        var mouseAdapter = new MouseAdapter() {
            private boolean paintColor = false;
            private boolean drawing = false;

            @Override
            public void mousePressed(MouseEvent e) {
                var pos = posFromMouse(e);
                int button = e.getButton();

                if (button == MouseEvent.BUTTON1) {
                    // left click
                    paintColor = !getPos(pos);
                    setPos(pos, paintColor);
                    drawing = true;
                    return;
                }
                drawing = false;

                if (button == MouseEvent.BUTTON3) {
                    // right click
                    setPos(pos, false);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (drawing) {
                    setPos(e, paintColor);
                }
            }
        };

        drawPanel.addMouseListener(mouseAdapter);
        drawPanel.addMouseMotionListener(mouseAdapter);

        add(drawPanel, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setVisible(true);
    }

    private GridPos posFromMouse(MouseEvent e) {
        int row = e.getY() / PIXEL_SIZE;
        int col = e.getX() / PIXEL_SIZE;

        row = Math.max(Math.min(row, GRID_HEIGHT - 1), 0);
        col = Math.max(Math.min(col, GRID_WIDTH - 1), 0);

        return new GridPos(col, row);
    }

    private boolean getPos(GridPos pos) {
        return pixels[pos.y][pos.x];
    }

    private boolean getPos(MouseEvent e) {
        return getPos(posFromMouse(e));
    }

    private void setPos(GridPos pos, boolean val) {
        if (pixels[pos.y][pos.x] != val) {
            pixels[pos.y][pos.x] = val;
            if (val) {
                nodeGrouper.addNode(pos.asLong());
            } else {
                nodeGrouper.delNode(pos.asLong());
            }
            drawPanel.repaint();
        }
    }

    private void setPos(MouseEvent e, boolean val) {
        setPos(posFromMouse(e), val);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PixelPainter::new);
    }
}