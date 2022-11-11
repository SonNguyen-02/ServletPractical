package com.mct.practical.practical3.data.server.dao.impl;

import com.mct.database.query.StatementFactory;
import com.mct.database.query.statement.Select;
import com.mct.practical.practical3.data.server.dao.ProductDao;
import com.mct.practical.practical3.data.server.database.connection.DatabaseConnection;
import com.mct.practical.practical3.domain.model.Product;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl extends Dao implements ProductDao {
    private static final String TABLE = "tblproduct";

    public ProductDaoImpl(DatabaseConnection connection) {
        super(connection);
    }

    @Override
    public long create(@NotNull Product product) {

        String sql = StatementFactory.createInsertStatement()
                .from(TABLE)
                .set("cat_id", product.getCategoryId())
                .set("pro_code", product.getCode())
                .set("pro_name", product.getName())
                .set("pro_description", product.getDesc())
                .set("pro_price", product.getPrice())
                .set("pro_galleries", product.getImage())
                .buildStatement();

        return execInsertAndClose(sql);
    }

    @Override
    public boolean update(@NotNull Product product) {

        String sql = StatementFactory.createUpdateStatement()
                .from(TABLE)
                .set("cat_id", product.getCategoryId())
                .set("pro_code", product.getCode())
                .set("pro_name", product.getName())
                .set("pro_description", product.getDesc())
                .set("pro_price", product.getPrice())
                .set("pro_galleries", product.getImage())
                .where("id = ?", product.getId())
                .buildStatement();

        return execUpdateAndClose(sql);
    }

    @Override
    public boolean delete(@NotNull Product product) {
        return delete(product.getId());
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
    public long getTotalProductBySearch(String search, Integer[] categoryIds) {

        Select select = StatementFactory.createSelectStatement()
                .column("COUNT(1) as total")
                .from(TABLE + " prd")
                .join("tblcategory cat", "prd.cat_id = cat.id")
                .whereIn("cat_id", (Object[]) categoryIds);

        if (search != null) {
            select.groupStart();
            select.like("pro_code", search).orLike("pro_name", search).orLike("cat_name", search);
            if (search.contains(" ")) {
                for (String s : search.split(" ")) {
                    if ((s = s.trim()).isEmpty()) continue;
                    select.orLike("pro_code", s).orLike("pro_name", s).orLike("cat_name", s);
                }
            }
            select.groupEnd();
        }

        long total = 0;
        String sql = select.buildStatement();

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
    public List<Product> getProducts(String search, Integer[] categoryIds, int limit, int offset) {
        List<Product> products = new ArrayList<>();

        Select select = StatementFactory.createSelectStatement()
                .column("prd.*, cat.cat_name")
                .from(TABLE + " prd")
                .join("tblcategory cat", "prd.cat_id = cat.id")
                .whereIn("cat_id", (Object[]) categoryIds)
                .orderByDesc("id")
                .limit(limit, offset);

        if (search != null) {
            select.groupStart();
            select.like("pro_code", search).orLike("pro_name", search).orLike("cat_name", search);
            if (search.contains(" ")) {
                for (String s : search.split(" ")) {
                    if ((s = s.trim()).isEmpty()) continue;
                    select.orLike("pro_code", s).orLike("pro_name", s).orLike("cat_name", s);
                }
            }
            select.groupEnd();
        }

        String sql = select.buildStatement();

        try (ResultSet rs = execSelect(sql)) {
            while (rs.next()) {
                Product product = _parseFromResultSet(rs);
                products.add(product);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return products;
    }

    @Override
    public Product getProduct(long id) {

        String sql = StatementFactory.createSelectStatement()
                .column("prd.*, cat.cat_name")
                .from(TABLE + " prd")
                .join("tblcategory cat", "prd.cat_id = cat.id")
                .where("prd.id = ?", id)
                .buildStatement();

        Product product = null;

        try (ResultSet rs = execSelect(sql)) {
            if (rs.next()) {
                product = _parseFromResultSet(rs);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return product;
    }

    @NotNull
    private Product _parseFromResultSet(@NotNull ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setCategoryId(rs.getInt("cat_id"));
        product.setCode(rs.getString("pro_code"));
        product.setName(rs.getString("pro_name"));
        product.setDesc(rs.getString("pro_description"));
        product.setPrice(rs.getFloat("pro_price"));
        product.setImage(rs.getString("pro_galleries"));
        product.setCategoryName(rs.getString("cat_name"));
        return product;
    }

}
