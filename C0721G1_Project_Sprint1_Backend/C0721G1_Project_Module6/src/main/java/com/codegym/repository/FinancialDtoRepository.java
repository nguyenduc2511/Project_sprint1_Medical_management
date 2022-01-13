package com.codegym.repository;

import com.codegym.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FinancialDtoRepository extends JpaRepository<Warehouse,Long> {

    //    -- querry tien ban hang
    @Query(nativeQuery = true, value = "select SUM(o.quantity*s.price) as income " +
            "from order_detail o join supplies s on s.id = o.supplies_id;")
    Integer getIncome();

    //    -querry hoantien
    @Query(nativeQuery = true, value = "select SUM(broken_supplies*price) as retund\n" +
            "from warehouse;")
    Integer getReturn();

    //    query tien nhap hang
    @Query(nativeQuery = true, value = "select SUM(quantity*price) as import\n" +
            "from warehouse;")
    Integer getImport();

    //    query tien huy hang
    @Query(nativeQuery = true, value = "select SUM(cancelled_supplies*price) as cancelled\n" +
            "from warehouse;")
    Integer getCancelled();


    //    query tien tra lai khach hang
    @Query(nativeQuery = true, value = "select SUM(refund_supplies*price) as refunded\n" +
            "from warehouse;")
    Integer getRefund();

    //    -- querry tien ban hang theo thang
    @Query(nativeQuery = true, value = "select o.quantity*s.price as month_sales\n" +
            "from order_detail o join supplies s on o.supplies_id = s.id \n" +
            "where order_date like :date")
    Integer getMonthSales(String date);



    @Query(nativeQuery = true, value = "select SUM(quantity*price) as month_import\n" +
            "from warehouse\n" +
            "where import_date like :date")
    Integer getMonthImport(String date);



    @Query(nativeQuery = true, value = "select SUM(broken_supplies*price) as retund\n" +
            "from warehouse\n" +
            "where import_date like :date")
    Integer getMonthReturn(String date);


    @Query(nativeQuery = true, value = "select SUM(refund_supplies*price) as refunded\n" +
            "from warehouse\n" +
            "where import_date like :date")
    Integer getMonthRefund(String date);


    @Query(nativeQuery = true, value = "select SUM(cancelled_supplies*price) as cancelled\n" +
            "from warehouse\n" +
            "where import_date like :date")
    Integer getMonthCancelled(String date);

}
