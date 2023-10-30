package org.example;

import com.mongodb.Block;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        try (var mongoClient = MongoClients.create()) {

            var database = mongoClient.getDatabase("syn"); // Обращаемся к базе и называем ее

            var todoCollection = database.getCollection("todo"); // Получаем коллекцию

            todoCollection.find() // Пробуем поискать все что у нас есть
                    .forEach((Consumer<Document>) System.out::println);

            todoCollection.insertOne(new Document(Map.of( // // Создаем документ и вставляем элемент (документ)
                    "_id", new ObjectId(),
                    "task", "Drink some coffee",
                    "dateCreated", LocalDateTime.now(),
                    "done", false)));


            todoCollection.updateOne(new Document("_id", new ObjectId("653c53035eb7902af009178b")), // Изменение документа
                    new Document(Map.of(
                            "$set", new Document("done", true),
                            "$currentDate", new Document("dateDone", true),
                            "$unset", new Document("dateCreated", true))));


            todoCollection.deleteMany(new Document("_id", new ObjectId("653c53035eb7902af009178b"))); // Удаление документа

            //todoCollection.aggregate() // Вписываем функцию агрегации в правильном формате из монго експресса (принимает Bson)
        }
    }
}

