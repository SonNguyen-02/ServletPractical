package com.mct.practical.practical3.data.server.dao.impl;

import com.mct.database.query.StatementFactory;
import com.mct.database.query.enums.LikeType;
import com.mct.practical.practical3.data.server.dao.CategoryDao;
import com.mct.practical.practical3.data.server.database.connection.DatabaseConnection;
import com.mct.practical.practical3.domain.model.Category;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends Dao implements CategoryDao {

    private static final String TABLE = "tblcategory";

    public CategoryDaoImpl(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    public long create(@NotNull Category category) {

        String sql = StatementFactory.createInsertStatement()
                .from(TABLE)
                .set("cat_name", category.getName())
                .set("cat_description", category.getDesc())
                .buildStatement();

        return execInsertAndClose(sql);
    }

    @Override
    public boolean update(@NotNull Category category) {

        String sql = StatementFactory.createUpdateStatement()
                .from(TABLE)
                .set("cat_name", category.getName())
                .set("cat_description", category.getDesc())
                .where("id = ?", category.getId())
                .buildStatement();

        return execUpdateAndClose(sql);
    }

    @Override
    public boolean delete(@NotNull Category category) {
        return delete(category.getId());
    }

    @Override
    public boolean delete(long id) {

        String sql = StatementFactory.createDeleteStatement()
                .from(TABLE)
                .where("id = ?", id)
                .buildStatement();

        return execUpdateAndClose(sql);
    }

    @Override
    public long getTotalCategoryBySearch(String search) {

        String sql = StatementFactory.createSelectStatement()
                .column("COUNT(1) as total")
                .from(TABLE)
                .like("cat_name", search, LikeType.BOTH)
                .buildStatement();

        long total = 0;

        try (ResultSet rs = execSelect(sql)) {
            if (rs.next()) total = rs.getLong("total");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return total;
    }

    @Override
    public List<Category> getCategories(String search, int limit, int offset) {

        List<Category> categories = new ArrayList<>();

        String sql = StatementFactory.createSelectStatement()
                .from(TABLE)
                .like("cat_name", search, LikeType.BOTH)
                .orderByDesc("id")
                .limit(limit, offset)
                .buildStatement();

        try (ResultSet rs = execSelect(sql)) {
            while (rs.next()) {
                Category category = _parseFromResultSet(rs);
                categories.add(category);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return categories;
    }

    @Override
    public Category getCategory(long id) {

        String sql = StatementFactory.createSelectStatement()
                .from(TABLE)
                .where("id = ?", id)
                .buildStatement();

        Category category = null;

        try (ResultSet rs = execSelect(sql)) {
            if (rs.next()) {
                category = _parseFromResultSet(rs);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return category;
    }

    @Override
    public boolean isExists(long id, String search) {

        String sql = StatementFactory.createSelectStatement()
                .column("COUNT(1) as total")
                .from(TABLE)
                .where("id != ?", id)
                .where("cat_name = ?", search)
                .buildStatement();

        long total = 0;

        try (ResultSet rs = execSelect(sql)) {
            if (rs.next()) total = rs.getLong("total");
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return total > 0;
    }

    @NotNull
    private Category _parseFromResultSet(@NotNull ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("cat_name"));
        category.setDesc(rs.getString("cat_description"));
        return category;
    }
}
