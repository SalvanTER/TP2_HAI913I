package hai913i.main.java.cli;

import hai913i.main.java.identification.Identification;

public class CLIPrintIdentification extends IChoice{
	Identification i;
	public CLIPrintIdentification(String title, Identification i) {
		super(title);
		this.i = i;
	}
	@Override
	public void todo() {
		i.printResult();
	}

}
