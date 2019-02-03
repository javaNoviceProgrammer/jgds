package jgds.layout.gui;

import ch.epfl.general_libraries.experiment_aut.Experiment;
import ch.epfl.general_libraries.experiment_aut.WrongExperimentException;
import ch.epfl.general_libraries.results.AbstractResultsDisplayer;
import ch.epfl.general_libraries.results.AbstractResultsManager;
import ch.epfl.javancox.experiments.builder.ExperimentConfigurationCockpit;
import jgds.layout.Cell;
import jgds.util.DataBase;

public class CreateNewCell implements Experiment {

	Cell cell ;

	public CreateNewCell(
			Cell cell
			) {
		this.cell = cell ;
	}

	@Override
	public void run(AbstractResultsManager man, AbstractResultsDisplayer dis) throws WrongExperimentException {
		cell.saveGds();
		AbstractResultsDisplayer.showGUI = false ;
		DataBase.clearDataBase();
	}

	public static void main(String[] args) {
		String pkg = "jgds" ;
		String cls = CreateNewCell.class.getName() ;
		String[] params = {"-p", pkg, "-c", cls} ;
		ExperimentConfigurationCockpit.execute(params, true);
	}

}
