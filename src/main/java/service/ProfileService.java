package service;

import database.DatabaseClass;
import model.Profile;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {

    private final DatabaseClass db = new DatabaseClass();

    public List<Profile> getAllProfiles() {
        var profilesList = new ArrayList<Profile>();
        String query = "select * from profile";
        try {
            var resultSet = db.getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (resultSet.next()) {
                var profile = new Profile();


                profile.setId(resultSet.getInt("id"));
                profile.setFirstName(resultSet.getString("firstName"));
                profile.setLastName(resultSet.getString("lastName"));
                profile.setAge(resultSet.getInt("age"));
                profile.setCreated(resultSet.getDate("created").toLocalDate());

                profilesList.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profilesList;
    }

    public Profile getProfile(int id) {
        var profile = new Profile();
        String query = "select * from profile where id = " + id;
        try {
            var resultSet = db.getConnection()
                    .createStatement()
                    .executeQuery(query);

            while (resultSet.next()) {

                profile.setId(resultSet.getInt("id"));
                profile.setFirstName(resultSet.getString("firstName"));
                profile.setLastName(resultSet.getString("lastName"));
                profile.setAge(resultSet.getInt("age"));
                profile.setCreated(resultSet.getDate("created").toLocalDate());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profile;
    }

    public Profile addProfile(Profile profile) {
        String query = ("insert into profile values (?,?,?,?,?)");

        try {
            var statement = db.getConnection().prepareStatement(query);
            statement.setInt(1, profile.getId());
            statement.setString(2, profile.getFirstName());
            statement.setString(3, profile.getLastName());
            statement.setInt(4, profile.getAge());
            statement.setDate(5, Date.valueOf(profile.getCreated()));
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        String query = ("update profile set firstName = ?, lastName = ?, age = ?, created = ? where id = ?");

        try {
            var statement = db.getConnection().prepareStatement(query);
            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setInt(3, profile.getAge());
            statement.setDate(4, Date.valueOf(profile.getCreated()));
            statement.setInt(5, profile.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public void removeProfile(int id) {
        String query = ("delete from profile where id = ?");

        try {
            var preparedStatement = db.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
