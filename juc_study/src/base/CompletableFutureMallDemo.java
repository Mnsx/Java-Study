package base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/16 22:05
 * @Description:
 */
public class CompletableFutureMallDemo {

    static List<NetMail> list = Arrays.asList(
            new NetMail("jd"),
            new NetMail("dnagdang"),
            new NetMail("taobao")
    );

    public static List<String> getPrice(List<NetMail> list, String productName) {

        return list.stream()
                .map(netMail -> String.format(productName + "in %s price is %.2f",
                        netMail.getNetMailName(), netMail.calcPrice(productName)))
                .collect(Collectors.toList());
    }

    public static List<String> getPriceByCompletableFuture(List<NetMail> list, String productName) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        return list.stream()
                .map(netMail -> CompletableFuture.supplyAsync(() -> String.format(productName + "in %s price is %.2f",
                        netMail.getNetMailName(), netMail.calcPrice(productName)), threadPool)).collect(Collectors.toList())
                .stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // 同一款产品，同时搜索出同款产品在各大电商平台的售价
        long startTime = System.currentTimeMillis();
        List<String> mysql = getPriceByCompletableFuture(list, "mysql");
        for (String s : mysql) {
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("---costTime" + (endTime - startTime) + "ms");
    }
}

class NetMail {
    @Getter
    private String netMailName;

    public NetMail(String netMailName) {
        this.netMailName = netMailName;
    }

    public double calcPrice(String produceName) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ThreadLocalRandom.current().nextDouble() * 2 + produceName.charAt(0);
    }
}