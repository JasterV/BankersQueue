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
    private BankSimulator bank = new BankSimulator();

    private ChartBuilder() {
        builder();
    }

    private void builder() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        BufferedImage image = chart.createBufferedImage(1200, 700);
        buildImage(image);
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "BankersQueue",
                "stage",
                "Minutes",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        bank.run();
        dataset.setValue(bank.getMean(0) / 60, "Minutes", "1 banker");
        dataset.setValue(bank.getMean(1) / 60, "Minutes", "2 bankers");
        dataset.setValue(bank.getMean(2) / 60, "Minutes", "3 bankers");
        dataset.setValue(bank.getMean(3) / 60, "Minutes", "4 bankers");
        dataset.setValue(bank.getMean(4) / 60, "Minutes", "5 bankers");
        dataset.setValue(bank.getMean(5) / 60, "Minutes", "6 bankers");
        dataset.setValue(bank.getMean(6) / 60, "Minutes", "7 bankers");
        dataset.setValue(bank.getMean(7) / 60, "Minutes", "8 bankers");
        dataset.setValue(bank.getMean(8) / 60, "Minutes", "9 bankers");
        dataset.setValue(bank.getMean(9) / 60, "Minutes", "10 bankers");
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
