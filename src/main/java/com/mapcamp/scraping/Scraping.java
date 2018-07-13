package com.mapcamp.scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mapcamp.domain.entity.Product;
import com.mapcamp.domain.service.ProductService;

@Component
public class Scraping {

    @Autowired
    private ProductService productService;

    private static final String SITE_URL = "https://r.gnavi.co.jp/area/aream4862/rs/?p=2";

    public void execute() throws IOException {
        List<String> links = collectPageLink();
        for (String link : links) {
            saveProduct(link);
        }
    }

    private List<String> collectPageLink() throws IOException {
        List<String> links = new ArrayList<>();
        Document document = Jsoup.connect(SITE_URL).get();
        Elements elements = document.select(".result-cassette__box-photo-list-item a");
        for (Element element : elements) {
            links.add(element.attr("href"));
        }
        return links;
    }

    private void saveProduct(String link) throws IOException {
        Document document = Jsoup.connect(link).get();
        //String shopName = document.select(".shop-info__name").first();
        String imageUrl = document.select(".motif-unit img").first().attr("src");

        Product product = new Product();
        product.setShopUrl(link);
        product.setImageUrl("http:"+imageUrl);
        productService.save(product);
    }

}
