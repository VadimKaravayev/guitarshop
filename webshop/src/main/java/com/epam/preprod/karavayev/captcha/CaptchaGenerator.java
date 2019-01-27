package com.epam.preprod.karavayev.captcha;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

public class CaptchaGenerator {

    private long timeout;

    public CaptchaGenerator(long timeout) {
        this.timeout = timeout;
    }

    public Captcha generateCaptcha() {
        Captcha captcha = new Captcha();
        captcha.setValue(generateRandomNumbers(6));
        captcha.setImage(drawPicture(captcha.getValue()));
        captcha.setExpirationDate(LocalDateTime.now().plusSeconds(timeout));
        return captcha;
    }

    private BufferedImage drawPicture(String captchaValue) {

        char[] data = captchaValue.toCharArray();
        int width = 150;
        int height = 50;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.setFont(new Font("Georgian", Font.BOLD, 18));

        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(renderingHints);
        GradientPaint gradientPaint = new GradientPaint(0, 0, Color.red, 0, height/2, Color.black, true);

        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(255, 153, 0));

        Random random = new Random();
        int x = 0;
        int y = 0;

        for (int i = 0; i < data.length; i++) {
            x += 10 + (Math.abs(random.nextInt()) % 15);
            y = 20 + Math.abs(random.nextInt()) % 20;
            g2d.drawChars(data, i, 1, x, y);
        }

        g2d.dispose();

        return bufferedImage;
    }

    private String generateRandomNumbers(int sequenceSize) {
        IntStream ints = new Random().ints(sequenceSize, 0, 10);
        StringBuilder result = new StringBuilder();
        ints.forEach(result::append);
        return result.toString();
    }
}
