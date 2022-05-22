
public class DNA {
	char[] genes;
	int fitness;
	
	//Construct a new DNA strand
	public DNA(int numGenes) {
		genes = new char[numGenes];
		for(int i = 0; i < numGenes; i++) {
			genes[i] = newChar();
		}
	}
	
	//Get a random char within range
	private char newChar() {
		int ascii = (int)Math.floor(Math.random()*(63) + 59);
		if(ascii == 63) {
			ascii = 32;
		}
		if(ascii == 64) {
			ascii = 46;
		}
		return (char)ascii;
	}
	
	//Get string representation of char array (DNA)
	public String GetPhrase() {
		String result = "";
		for(char c: this.genes) {
			result = result+c;	
		}
		return result;
	}
	
	//Get fitness of current DNA
	public double GetFitness(String target) {
		double score = 0;
		for(int i = 0; i < this.genes.length; i++) {
			if(this.genes[i] == (target.charAt(i))) {
				score++;
			}
		}
		return score/target.length();
	}
	
	//Crossover this DNA with a partner, and return a child dna
	public DNA Crossover(DNA partner) {
		DNA child = new DNA(this.genes.length);	
		int midPoint = (int)Math.floor(Math.random()*this.genes.length);
		
		for(int i = 0; i < this.genes.length; i++) {
			if(i > midPoint) {
				child.genes[i] = this.genes[i];
			}
			else {
				child.genes[i] = partner.genes[i];
			}
		}
		return child;
	}
	
	//Mutate individual chars in DNA strand based on specified mutation rate
	public void Mutate(double mutationRate) {
		for(int i = 0; i < this.genes.length; i++) {
			if(Math.random() < mutationRate) {
				this.genes[i] = newChar();
			}
		}
	}
}
