package biz.svyatoslav.learning.bigdata.elasticsearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Random;

// 1) Make sure that your Elasticsearch, Logstash and Kibana containers are running (see "readme!/docker-compose.yml")
// 2) Make sure that ip addresses are correct in "logstash_es.conf" and "log4j2.xml".

public class Log4jSimpleAppWithKibana {
    private static final Logger logger = LogManager.getLogger(biz.svyatoslav.learning.bigdata.elasticsearch.Log4jSimpleAppWithKibana.class);
    private static LocalDateTime now = null;

    public static void main(String[] args) {

        Random randomTime = new Random();
        Random randomChoice = new Random();

        System.out.println("Logging test messages (press Ctrl+C to stop)...");
        now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formattedDate = now.format(dateFormatter);

        System.out.println("Done.");
        System.out.println("Visit http://localhost:9200/_cat/indices for indexes list, you should see something like 'spark-logs-" + formattedDate + "' there.");
        System.out.println("Visit http://localhost:9200/spark-logs-" + formattedDate + "/_search?pretty to see the result. Change the date in the index name!");
        System.out.println("Visit http://localhost:5601/app/management/data/index_management/indices to view ES indexes in Kibana.");
        System.out.println("View 'readme!/manual.docx' for more specific instructions.");

        while (true) {
            try {
                int choice = randomChoice.nextInt(5); // Random number between 0 and 4

                now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);

                switch (choice) {
                    case 0:
                        logger.debug("This is a DEBUG message.");
                        System.out.println(formattedDateTime + " Logging DEBUG message to Elasticsearch via Logstash.");
                        break;
                    case 1:
                        logger.info("This is an INFO message.");
                        System.out.println(formattedDateTime + " Logging INFO message to Elasticsearch via Logstash.");
                        break;
                    case 2:
                        logger.warn("This is a WARN message.");
                        System.out.println(formattedDateTime + " Logging WARN message to Elasticsearch via Logstash.");
                        break;
                    case 3:
                        logger.error("This is an ERROR message.");
                        System.out.println(formattedDateTime + " Logging ERROR message to Elasticsearch via Logstash.");
                        break;
                    case 4:
                        logger.fatal("This is a FATAL message.");
                        System.out.println(formattedDateTime + " Logging FATAL message to Elasticsearch via Logstash.");
                        break;
                }

                // Generate a random delay interval (e.g., between 1 to 5 seconds)
                int delay = 1000 + randomTime.nextInt(4000); // Delay in milliseconds

                // Sleep for the random interval
                // Yes, this is a dummy approach, yet it is simple :)
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
}