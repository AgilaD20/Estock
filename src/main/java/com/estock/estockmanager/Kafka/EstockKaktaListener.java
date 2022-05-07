package com.estock.estockmanager.Kafka;


import com.estock.estockmanager.repository.StockInfoRepositoryImpl;
import com.estock.estockmanager.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EstockKaktaListener {

    private StockService stockService;

    private StockInfoRepositoryImpl stockInfoRepository;

    public EstockKaktaListener(StockService stockService, StockInfoRepositoryImpl stockInfoRepository){
        this.stockService = stockService;
        this.stockInfoRepository = stockInfoRepository;

    }

    @KafkaListener(topics ="${kafka.stock.add.topic}",groupId = "${kafka.stock.add.groupid}")
    public void consumeEStockMessage(ConsumerRecord<String,String> consumerRecord){
        log.info("Received stock to add");
        stockService.addstock(Double.parseDouble(consumerRecord.value()),consumerRecord.key());
    }

    @KafkaListener(topics ="${Kafka.stock.delete.topic}",groupId = "${kafka.stock.delete.groupid}")
    public void ConsumeDeleteEStockMessage(ConsumerRecord<String,String> consumerRecord){
        log.info("Received stock to delete");
        stockInfoRepository.deleteAllByCompanyCode(consumerRecord.value());
    }

}
