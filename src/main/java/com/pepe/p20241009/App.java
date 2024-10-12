package com.pepe.p20241009;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.pepe.p20241009.Models.Componente;
import com.pepe.p20241009.Models.Proveedor;
/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        /* Inserción de registros
        Componente comp = new Componente();
        comp.setC("B2");
        comp.setNombre("Componente borrable 2");
        comp.setColor("NEGRO");
        comp.setPeso(20);
        comp.setCiudad("LA PAZ");
        Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        sesion.save(comp);
        tx.commit();
        System.out.println("Exito componente!!");
        
        Proveedor pro = new Proveedor();
        pro.setP("BR");
        pro.setPnombre("Borrar por favor");
        pro.setCategoria(0);
        pro.setCiudad("ORURO");
         tx= null;
         sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        sesion.save(pro);
        tx.commit();
        System.out.println("Exito proveedor!!");
        */
    	//mostrarRegistroProveedores();
    	mostrarRegistroProveedoresWhere();
    	mostrarRegistroProveedoresOrderBy();
    	//ejemplo group by
    }

	private static void mostrarRegistroProveedoresOrderBy() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "FROM Proveedor AS pro ORDER BY pro.ciudad DESC, pro.pnombre ASC"; // SELECT pro.* FROM Proveedor pro;
        Query consulta = sesion.createQuery(hql);
        //List proveedores = sesion.createQuery("FROM com.pepe.p20241009.Models.Proveedor").list();
        List proveedores = consulta.getResultList();
        for(Iterator iterator= proveedores.iterator();iterator.hasNext();) {
        	Proveedor p = (Proveedor) iterator.next();
        	System.out.println(String.format("Código: %s, nombre: %s (%s)", p.getP(), p.getPnombre(),p.getCiudad()));
        }
        tx.commit();
        System.out.println("Exito lectura proveedor!!");
	}

	private static void mostrarRegistroProveedoresWhere() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "FROM Proveedor AS pro WHERE pro.ciudad = 'LA PAZ'"; // SELECT pro.* FROM Proveedor pro;
        Query consulta = sesion.createQuery(hql);
        //List proveedores = sesion.createQuery("FROM com.pepe.p20241009.Models.Proveedor").list();
        List proveedores = consulta.getResultList();
        for(Iterator iterator= proveedores.iterator();iterator.hasNext();) {
        	Proveedor p = (Proveedor) iterator.next();
        	System.out.println(String.format("Código: %s, nombre: %s (%s)", p.getP(), p.getPnombre(),p.getCiudad()));
        }
        tx.commit();
        System.out.println("Exito lectura proveedor!!");
	}

	private static void mostrarRegistroProveedores() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "SELECT new com.pepe.p20241009.Models.ProveedorSimple(pro.p, pro.pnombre, pro.ciudad) FROM Proveedor AS pro"; // SELECT pro.* FROM Proveedor pro;
        Query consulta = sesion.createQuery(hql);
        //List proveedores = sesion.createQuery("FROM com.pepe.p20241009.Models.Proveedor").list();
        List proveedores = consulta.getResultList();
        for(Iterator iterator= proveedores.iterator();iterator.hasNext();) {
        	com.pepe.p20241009.Models.ProveedorSimple p = (com.pepe.p20241009.Models.ProveedorSimple) iterator.next();
        	System.out.println(String.format("Código: %s, nombre: %s (%s)", p.getP(), p.getPnombre(),p.getCiudad()));
        }
        tx.commit();
        System.out.println("Exito lectura proveedor!!");
	}
}
