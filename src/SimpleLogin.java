
import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Entity
@Table(name = "users")
@ManagedBean(name = "SimpleLogin")
public class SimpleLogin {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String loginname;

	@Column(name = "password")
	private String password;

	public SimpleLogin() {

	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String CheckValidUser() {

		SessionFactory factory = new Configuration().configure().addAnnotatedClass(SimpleLogin.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		session.beginTransaction();
		/*
		 * if (loginname.equals("mert") && password.equals("123")) {
		 * System.out.println("chandan"); return "success"; } else { return
		 * "fail"; }
		 */

		Query query = session.createQuery("from SimpleLogin where loginname=:loginname and password=:password");
		query.setString("loginname", loginname);
		query.setString("password", password);

		List list = query.getResultList();

		session.getTransaction().commit();

		if (list.size() == 1) {
			return "success";
		} else {
			return "fail";
		}

	}
}