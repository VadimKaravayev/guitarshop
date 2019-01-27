package com.epam.preprod.karavayev.shop.inputproduct.director;

import com.epam.preprod.karavayev.shop.inputproduct.ProductInputStrategy;

public interface InputStrategyDirector {
    ProductInputStrategy getInputStrategy(String product);
}
