import java.util.ArrayList;
public class Population {
	DNA[] population;
	ArrayList<DNA> matingPool;
	boolean finished;
	String target;
	String best;
	double mutationRate;
	int generations;
	double perfectScore;
	public Population(String target, double mutRate, int populationCount) {
		//Initialize variables
		this.population = new DNA[populationCount];
		this.matingPool = new ArrayList<DNA>();
		this.generations = 0;
		this.finished = false;
		this.target = target;
		this.mutationRate = mutRate;
		this.perfectScore = 1;
		this.best = "";
		
		//populate the population array
		this.populatePopulation();
	}
	
	private void populatePopulation() {
		for(int i = 0; i < population.length; i++) {
			this.population[i] = new DNA(this.target.length());
		}
	}
	
	public void NaturalSelection() {
		this.matingPool.clear();//clear mating pool
		
		//get the fittest DNA from population
		double maxFitness = 0;
		double temp = 0;
		for(int i = 0; i < this.population.length; i++) {
			temp = this.population[i].GetFitness(this.target);
			if(temp > maxFitness) {
				maxFitness = temp;
			}
		}
		
		double relativeFitness;
		for(int i = 0; i < this.population.length; i++) {
			relativeFitness = (this.population[i].GetFitness(this.target))/(maxFitness+1)*10 + 1;

			for(int j = 0; j < relativeFitness; j++) {
				//push the current DNA strand into mating pool ('n' times)
				this.matingPool.add(this.population[i]);
			}
		}
	}	
	
	public void Generate() {
		int indA = 0;
		int indB = 0;
		DNA child = null;
		for(int i = 0; i < this.population.length; i++) {
			indA = (int)Math.floor(Math.random()*(this.matingPool.size()-1));
			indB = (int)Math.floor(Math.random()*(this.matingPool.size()-1));
			DNA PartnerA = this.matingPool.get(indA);
			DNA PartnerB = this.matingPool.get(indB);
			child = PartnerA.Crossover(PartnerB);
			child.Mutate(this.mutationRate);
			this.population[i] = child;
		}
		this.generations++;
	}
	
	public String GetBest() {
		return this.best;
	}
	
	public void Evaluate() {
		double record = 0;
		int index = 0;
		double temp = 0;
		
		for(int i = 0; i < this.population.length; i++) {
			temp = this.population[i].GetFitness(this.target);
			if(temp > record) {
				index = i;
				record = temp;
			}
		}
		
		this.best = this.population[index].GetPhrase();
		if(record==this.perfectScore) {
			this.finished = true;
		}
	}
	
	public boolean IsFinished() {
		return this.finished;
	}
	
	public int GetGenerations() {
		return this.generations;
	}
	
	public double GetAvgFitness() {
		double total = 0;
		for(DNA d:this.population) {
			total += d.GetFitness(this.target);
		}
		return total/this.population.length;
	}
	
	public String AllPhrases() {
		return "Shakesperian Monkey Theorem";
	}
}
