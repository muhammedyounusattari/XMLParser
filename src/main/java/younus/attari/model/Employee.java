package younus.attari.model;

public class Employee {

	private String id, sal;
	private String name, desig;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	@Override
	public String toString() {
		return "(id,name,sal,desig)=(" + this.id + "," + this.name + "," + this.sal + "," + this.desig + ")";
	}
}
