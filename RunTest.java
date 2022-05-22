
public class RunTest {

	public static void main(String[] args) {
		//Parameters
		String target = "To be or not to be is the question.";
		double mutRate = 0.01;
		int populationCount = 10000;
		
		//Create new population
		Population p = new Population(target, mutRate, populationCount);
		
		int count = 0;
		while(true) {
			//perform natural selection
			p.NaturalSelection();
			
			//Create generation
			p.Generate();
			
			//Evaluate
			p.Evaluate();
			
			//Output current best (fittest) specimen
			System.out.println(p.GetBest());
			
			//End loop if finished
			if(p.IsFinished()) {
				System.out.println("\ndone");
				break;
			}
		}
		
		System.out.println("Gen: " + p.GetGenerations());
	}

}
