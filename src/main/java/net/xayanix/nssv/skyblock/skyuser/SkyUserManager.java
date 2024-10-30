package net.xayanix.nssv.skyblock.skyuser;

import com.mongodb.async.client.FindIterable;
import lombok.Getter;
import net.xayanix.nssv.core.mongo.MongoConnection;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class SkyUserManager {

    @Getter
    private static SkyUserManager instance;

    @Getter
    private ConcurrentHashMap<String, SkyUser> skyUsers;

    public SkyUserManager() {
        instance = this;
        this.skyUsers = new ConcurrentHashMap<>();

        this.load();
    }

    public SkyUser getUser(String player){
        return skyUsers.get(player.toLowerCase());
    }

    public void load(){
        FindIterable<Document> iterable = MongoConnection.getInstance().getMongoDatabase().getCollection("skyblock-db").find();
        iterable.forEach(document -> {
            try {
                SkyUser user = MongoConnection.getInstance().getGson().fromJson(MongoConnection.getInstance().getGson().toJson(document), SkyUser.class);
                user.setChestInventory(Bukkit.createInventory(null, 54));
                user.setDisabledDrops(new CopyOnWriteArrayList<>());
                user.loaded();
                this.skyUsers.put(user.getName().toLowerCase(), user);
            } catch (Exception e) { }
        }, (result, t) -> {  });
    }

}
