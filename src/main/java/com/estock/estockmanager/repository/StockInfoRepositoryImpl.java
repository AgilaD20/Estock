package com.estock.estockmanager.repository;

import com.estock.estockmanager.Entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class StockInfoRepositoryImpl implements StockInfoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Stock findLatestStock(String companyCode) {
        Criteria criteria = Criteria.where("companyCode").in(companyCode);
        Query query = new Query(criteria);
        query.with(Sort.by(Sort.Direction.DESC, "timestamp"));
        return mongoTemplate.findOne(query,Stock.class);
    }

    @Override
    public List<Stock> retrieveStockDetails(String companyCode, Instant startDate, Instant endDate) {
        Criteria criteria = Criteria.where("companyCode").in(companyCode).andOperator(Criteria.where("timestamp").gte(startDate).lte(endDate));
        Query query = new Query(criteria);
        return mongoTemplate.find(query,Stock.class);
    }

    @Override
    public void deleteAllByCompanyCode(String companyCode) {
        Criteria criteria = Criteria.where("companyCode").in(companyCode);
        Query query = new Query(criteria);
        mongoTemplate.remove(query,Stock.class);

    }

    @Override
    public Stock save(Stock stock) {
        return mongoTemplate.save(stock);
    }

}
