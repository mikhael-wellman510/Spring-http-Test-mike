//package geteway.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.*;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.DateFormat;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//
//@Document(indexName = "product_elastic")
//@Setter
//@Getter
//@Builder(toBuilder = true)
//@ToString
//public class ProductElastic {
//
//    @Id
//    private String id;
//
//    @Field(type = FieldType.Text , name = "product_name")
//    private String productName;
//
//    @Field(type = FieldType.Text , name = "store_name")
//    private String storeName;
//
//    @Field(type = FieldType.Double)
//    private Double price;
//
//    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
//    private Instant createdAt;
//}
