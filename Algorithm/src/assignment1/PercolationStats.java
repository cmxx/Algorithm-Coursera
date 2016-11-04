import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	int n;		// Length
	int t;		// times of trial
	double[] chance;
	
	   public PercolationStats(int n, int trials){
		   // perform trials independent experiments on an n-by-n grid
		   if (n<=0 || trials <= 0) throw new IllegalArgumentException("Your n "
		   		+ "or trails value is less than 0");
		   this.n = n;
		   this.t = trials;
		   
		   int totalSites = n*n;	// number of sites (total)
		   chance = new double[t];
		   
		   // This loop run t trails.
		   for (int i=0; i<t; i++){
			   Percolation p = new Percolation(n);
			   int openNumber = 0;		// used to record how many sites are open
			   while (!p.percolates()){		// Run until percolate.
				   int r1 = StdRandom.uniform(1, n+1);
				   int r2 = StdRandom.uniform(1, n+1);
				   
				   if (!p.isOpen(r1, r2)){
					   p.open(r1, r2);
					   openNumber++;
				   }
			   }
			   chance[i] = ((double)openNumber)/totalSites;
		   }
		  
		   
		   
		   
	   }
	   public double mean(){
		   // sample mean of percolation threshold
		   return StdStats.mean(chance);
	   }
	   public double stddev(){
		   // sample standard deviation of percolation threshold
		   return StdStats.stddev(chance);
	   }
	   public double confidenceLo()    {
		   // low  endpoint of 95% confidence interval
		   // Formula: for 95% confidence, z = 1.96
		   // confidence = mean +/-  (1.96 * stdev / sqrt(n) ) 
		   // n is sample size which equals to number of trails.
		   return (mean() - (stddev()/Math.sqrt(t) * 1.96));
	   }
	   public double confidenceHi()       {
		   // high endpoint of 95% confidence interval
		   return (mean() + (stddev()/Math.sqrt(t) * 1.96));
	   }

	public static void main(String[] args) {
		int usern = Integer.parseInt(args[0]);
		int usert = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(usern,usert);
		
		System.out.println("mean = \t\t" + ps.mean());
		System.out.println("stddev =\t" + ps.stddev());
		System.out.println("mean = \t\t" + ps.confidenceLo() + ", "+ ps.confidenceHi());
	}

}
