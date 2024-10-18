package com.pepe.p20241009;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import com.pepe.p20241009.Models.Articulo;
import com.pepe.p20241009.Models.Componente;
import com.pepe.p20241009.Models.Envio;
import com.pepe.p20241009.Models.Proveedor;
import com.pepe.p20241009.Models.ProveedorGrupo;

import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
    	//mostrarRegistroProveedoresGroupBy();//revisar
    	mostrarRegistrosProveedoresByCiudad("ORURO");
    	mostrarRegistrosProveedoresByCiudad("LA PAZ");
    	
    	//actualizacionRegistroProveedor();
    	//eliminacionRegistroProveedor();
    	
    	paginacion(0);
    	paginacion(2);
    	
    	mostrarEnviosUsandoMapeoDeColecciones();
    	
    	mostrarEnviosByArticulo("T1");
    	
    	mostrarRegistrosProcedimientoAlmacenado();
    	
    	mostrarRegistrosComponentesFuncionConParam(15,18);
    	
    	//mostrarRegistrosComponentesFuncionConParam2(15,18);
    	
    	criteriaQuery();
    	criteriaQuery2();
    	criteriaQuery3();
    }
    private static void criteriaQuery3() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        HibernateCriteriaBuilder builder = sesion.getCriteriaBuilder();
        CriteriaQuery<Componente> cq = sesion.getCriteriaBuilder()
        		.createQuery(Componente.class);
        Root<Componente> raiz = cq.from(Componente.class);// SELECT * FROM componente;
        cq.select(raiz).where(builder.like(raiz.get("color"), "%O%"));
        List<Componente> comps = sesion.createQuery(cq).getResultList();
        for(Componente c : comps)
        	System.out.println(String.format("%s, %s (%s)", c.getNombre(), c.getColor(), c.getCiudad()));
	}
	private static void criteriaQuery2() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        HibernateCriteriaBuilder builder = sesion.getCriteriaBuilder();
        CriteriaQuery<Componente> cq = sesion.getCriteriaBuilder()
        		.createQuery(Componente.class);
        Root<Componente> raiz = cq.from(Componente.class);// SELECT * FROM componente;
        cq.select(raiz).where(builder.equal(raiz.get("color"), "ROJO"));
        List<Componente> comps = sesion.createQuery(cq).getResultList();
        for(Componente c : comps)
        	System.out.println(String.format("%s, %s (%s)", c.getNombre(), c.getColor(), c.getCiudad()));
	}
	private static void criteriaQuery() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        CriteriaQuery<Componente> cq = sesion.getCriteriaBuilder()
        		.createQuery(Componente.class);
        cq.from(Componente.class);// SELECT * FROM componente;
        List<Componente> comps = sesion.createQuery(cq).getResultList();
        for(Componente c : comps)
        	System.out.println(String.format("%s, %s (%s)", c.getNombre(), c.getColor(), c.getCiudad()));
	}

	private static void mostrarRegistrosComponentesFuncionConParam2(int min, int max) {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        /*
        ProcedureCall pc = sesion.createStoredProcedureCall("listarcomp");
        pc.registerParameter("minimo", Integer.class, ParameterMode.IN);
        pc.registerParameter("maximo", Integer.class, ParameterMode.IN);
        pc.getParameterRegistration("minimo");//.bindValue(min);
        */
        StoredProcedureQuery spq = sesion.createStoredProcedureQuery("produccion.listarcomp", Componente.class);
        spq.registerStoredProcedureParameter("minimo", Integer.class, ParameterMode.IN);
        spq.registerStoredProcedureParameter("maximo", Integer.class, ParameterMode.IN);
        spq.setParameter("minimo", (Integer)min);
        spq.setParameter("maximo", (Integer)max);
        List<Componente> compos = spq.getResultList();
        for(Componente c : compos)
        	System.out.println(String.format("%s, %s (%s)", c.getNombre(), c.getColor(), c.getCiudad()));
	}

	private static void mostrarRegistrosComponentesFuncionConParam(int min, int max) {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        Query consulta = sesion.createNativeQuery("SELECT * FROM produccion.listarcomp(?,?)", Componente.class);
        consulta.setParameter(1, min);
        consulta.setParameter(2, max);
        System.out.println("Componentes de acuerdo a valores mínimo y máximo de peso:");
        List<Componente> componentes = consulta.getResultList();
        for(Componente comp : componentes) {
        	System.out.println(
            		String.format("Componente: %s, Peso: %d kg, Color: %s (%s)", comp.getNombre(), comp.getPeso(), comp.getColor(), comp.getCiudad())	
            			);
        }
        tx.commit();
	}

	private static void mostrarRegistrosProcedimientoAlmacenado() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        //Query consulta = sesion.createNativeQuery("{CALL listar()}", Envio.class);
        Query consulta = sesion.createNativeQuery("SELECT * FROM produccion.listar()", Envio.class);
        List<Envio> envios = consulta.getResultList();
        System.out.println("Resultado de Procedimiento almacenado (función):");
        for(Envio env : envios) {
        	System.out.println(
            		String.format("Proveedor: %s, Componente: %s, Artículo: %s (%d)", env.getP().getPnombre(), env.getC().getNombre(), env.getT().getTnombre(), env.getCantidad())	
            			);
        }
        tx.commit();
	}

	private static void mostrarEnviosByArticulo(String IdArticulo) {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "FROM Articulo WHERE t = :c";
        Query consulta = sesion.createQuery(hql);
        consulta.setParameter("c", IdArticulo);
        Articulo art = (Articulo)consulta.uniqueResult();
        for(Iterator iterador=  art.getEnvios().iterator();iterador.hasNext();) {
        	Envio env = (Envio)iterador.next();
        	System.out.println(
        		String.format("Proveedor: %s, Componente: %s, Artículo: %s (%d)", env.getP().getPnombre(), env.getC().getNombre(), env.getT().getTnombre(), env.getCantidad())	
        			);
        }
	}

	private static void mostrarEnviosUsandoMapeoDeColecciones() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "FROM Articulo WHERE t = 'T1'";
        Query query = sesion.createQuery(hql);
        Articulo art = (Articulo) query.uniqueResult();
        System.out.println("Éxito leyendo Artículo");
        System.out.println(art.getTnombre());
        for(Iterator iterador=  art.getEnvios().iterator();iterador.hasNext();) {
        	Envio env = (Envio)iterador.next();
        	System.out.println("Cantidad de evíos: "+env.getCantidad());
        }
	}

	private static void paginacion(int inicio) {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "FROM Proveedor";
        Query query = sesion.createQuery(hql);
        query.setFirstResult(inicio); // cuenta desde 0
        query.setMaxResults(2);
        List resultado = query.list();
        System.out.println("Éxito en paginación");
        for(Iterator iterator= resultado.iterator();iterator.hasNext();) {
        	Proveedor p = (Proveedor) iterator.next();
        	System.out.println(String.format("Ciudad: %s ( %s )", p.getCiudad(), p.getP()));
        }
	}

	private static void eliminacionRegistroProveedor() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        Proveedor pr = sesion.get(Proveedor.class, "BR");
        sesion.delete(pr);
        tx.commit();
        /*Con HQL: DELETE FROM Proveedor WHERE p = :p
        Query consulta = sesion.createQuery(hql);
        consulta.setParameter("p", "BR");
        int resultado = consulta.executeUpdate();
        */ 
	}

	private static void actualizacionRegistroProveedor() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        Proveedor pr = sesion.get(Proveedor.class, "BR");
        pr.setCiudad("SANTA CRUZ");
        pr.setPnombre("Borrame por favor");
        sesion.update(pr);
        tx.commit();
        System.out.println("Exito en modificación de proveedor!!");
	}

	private static void mostrarRegistrosProveedoresByCiudad(String ciudad) {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "FROM Proveedor AS pro WHERE pro.ciudad = :c"; // SELECT pro.* FROM Proveedor pro;
        Query consulta = sesion.createQuery(hql);
        consulta.setParameter("c", ciudad);
        List proveedores = consulta.getResultList();
        for(Iterator iterator= proveedores.iterator();iterator.hasNext();) {
        	Proveedor p = (Proveedor) iterator.next();
        	System.out.println(String.format("Ciudad: %s ( %s )", p.getCiudad(), p.getP()));
        }
        tx.commit();
        System.out.println("Exito lectura proveedor!!");
	}

	private static void mostrarRegistroProveedoresGroupBy() {
		Transaction tx= null;
        Session sesion =HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
        String hql = "SELECT new com.pepe.p20241009.Models.ProveedorGrupo(pro.ciudad AS ciudad, COUNT(pro.ciudad) AS cantidad, SUM(pro.categoria) AS suma) FROM Proveedor AS pro GROUP BY pro.ciudad"; // SELECT pro.* FROM Proveedor pro;
        Query consulta = sesion.createQuery(hql);
        //List proveedores = sesion.createQuery("FROM com.pepe.p20241009.Models.Proveedor").list();
        List proveedores = consulta.getResultList();
        for(Iterator iterator= proveedores.iterator();iterator.hasNext();) {
        	ProveedorGrupo p = (ProveedorGrupo) iterator.next();
        	System.out.println(String.format("Grupo %s: %d miembro(s); sumatoria de categorías: %d", p.getCiudad(), p.getCantidad(),p.getSuma()));
        }
        tx.commit();
        System.out.println("Exito lectura proveedor!!");
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
