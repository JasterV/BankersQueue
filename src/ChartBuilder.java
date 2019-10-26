import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChartBuilder extends JFrame {
    private BankSimulator bk = new BankSimulator();

    public ChartBuilder() {
        builder();

    }

    private void builder() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        BufferedImage image = chart.createBufferedImage(1200, 700);
        buildImage(image);

    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart barChart = ChartFactory.createBarChart(
                "BankersQueue",
                "stage",
                "Minutes",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        bk.run();
        dataset.setValue(bk.getMean(0) / 60, "Minutes", "Stage 1");
        dataset.setValue(bk.getMean(1) / 60, "Minutes", "Stage 2");
        dataset.setValue(bk.getMean(2) / 60, "Minutes", "Stage 3");
        dataset.setValue(bk.getMean(3) / 60, "Minutes", "Stage 4");
        dataset.setValue(bk.getMean(4) / 60, "Minutes", "Stage 5");
        dataset.setValue(bk.getMean(5) / 60, "Minutes", "Stage 6");
        dataset.setValue(bk.getMean(6) / 60, "Minutes", "Stage 7");
        dataset.setValue(bk.getMean(7) / 60, "Minutes", "Stage 8");
        dataset.setValue(bk.getMean(8) / 60, "Minutes", "Stage 9");
        dataset.setValue(bk.getMean(9) / 60, "Minutes", "Stage 10");
        return dataset;
    }

    private void buildImage(BufferedImage image) {
        try {
            ImageIO.write(image, "png", new File("queue.png"));
        } catch (IOException exc) {
            System.out.println("Error creating image.");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ChartBuilder ex = new ChartBuilder();
        });
    }
}
