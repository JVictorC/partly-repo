package com.jvictorc.partly.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KabumServices {
    public static String getURlByComponent(ComponentsType type) {
        switch (type) {
            case PLACA_DE_VIDEO:
                return "/hardware/placa-de-video-vga";
            case GABINETE:
                return "/perifericos/gabinetes";
            case PROCESSADOR:
                return "/busca/Memoria-Ram";
            case MEMORIA_RAM:
                return "/busca/memoria-ram";
            case HD:
                return "/hardware/disco-rigido-hd";
            case SSD:
                return "/hardware/ssd-2-5";
            case REFRIGERACAO:
                return "/hardware/coolers";
            case FONTE:
                return "/busca/fontes";
            case PLACA_MAE:
                return "/busca/placa-mãe";
            default:
                return "/ofertas/techlist";
        }
    }

    public static List<Component> getComponentInfos(ComponentsType type) {
        Document doc;
        var baseUrl = "https://www.kabum.com.br";
        var urlByComponent = getURlByComponent(type);

        System.out.println(urlByComponent);

        try {
            doc = Jsoup.connect(baseUrl.concat(urlByComponent + "?page_number=1&page_size=50&facet_filters=&sort=most_searched")).get();
            Elements allGabinetesDocs = doc.select("div.productCard");

            List<Component> allComponentes = new ArrayList<Component>();

            for (Element element : allGabinetesDocs) {
                var componente = new Component();


                componente.setUrlProduto(baseUrl.concat(element.selectFirst("a.htpbqG").attr("href")));
                componente.setImage(element.selectFirst("img.imageCard").attr("src"));
                componente.setNome(element.selectFirst("span.nameCard").text());
                componente.setPreco(element.selectFirst("span.priceCard").text());

                var docAvaliacoes = element.selectFirst("div.labelTotalAvaliacoes");

                if (docAvaliacoes != null) {
                    var avalaicoesText = docAvaliacoes.text();

                    avalaicoesText = avalaicoesText.substring(1, avalaicoesText.length() - 1);

                    componente.setAvaliacoes(avalaicoesText);
                } else {
                    componente.setAvaliacoes("0");
                }

                var isNotAvaliable = element.selectFirst("a.unavailableFooterCard");

                componente.setAvaliable(isNotAvaliable == null);


                allComponentes.add(componente);
            }

            allComponentes = filterList(type, allComponentes);

            return allComponentes;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private static List<Component> filterList(ComponentsType type, List<Component> allComponentes) {
        switch (type) {
            case FONTE -> {
                return  allComponentes.stream().filter(component -> component.getNome().toLowerCase().startsWith("fonte")).toList();
            }
        }

        return allComponentes;
    }
}
