
// Title: MovieDB
// Author: Doruk Arslan 
// Description: this class defines intended queries, movie,actor objects and creates movie database hold by binary search tree named movie Tree
import java.util.ArrayList;

public class MovieDatabase {

	// to create main tree that holds all movies
	BinaryST MovieTree = new BinaryST();

	// in order to keep movie datas as value in the tree we are creating movie class
	// in order to obtain movie objects
	// in this class we have required variables setter getters and a binary search
	// three for every movie objet in order to hold actors from the movie
	public class Movie {

		public String title;
		public String directorF;
		public String directorL;
		public int Day, Month, Year;

		BinaryST ActorTree = new BinaryST();

		public Movie(String title, String directorF, String directorL, int day, int month, int year) {

			this.title = title;
			this.directorF = directorF;
			this.directorL = directorL;
			this.Day = day;
			this.Month = month;
			this.Year = year;

		}

		public String getDirectorF() {
			return directorF;
		}

		public String getDirectorL() {
			return directorL;
		}

		public int getDay() {
			return Day;
		}

		public int getMonth() {
			return Month;
		}

		public int getYear() {
			return Year;
		}

		@Override
		public String toString() {
			return title + ", " + Year + ", " + directorF + " " + directorL;

		}

	}

	// likewise we have actor class to keep actor objects in actor tree
	public class Actor {
		String movie;
		String firstName;
		String LastName;
		String role;

		public Actor(String mov, String first, String L, String rol) {
			movie = mov;
			firstName = first;
			LastName = L;
			role = rol;
		}

		@Override
		public String toString() {
			return firstName + " " + LastName + "," + role;

		}

	}

	// Note that,there is no operation on trees depending on the arraylists.They
	// just exist in order to keep "Objects " that will be added or removed from the
	// tree.Such as array of key-value pairs that we learnt in the course.
	// Means that their purpose is prevent complex compare opartions, nothing more.
	// to add movies key to arraylist
	ArrayList<Integer> keyA = new ArrayList<Integer>();
	// to add movies name to the arratlist
	ArrayList<String> valA = new ArrayList<String>();
	// to add movie object to the arraylist
	ArrayList<Movie> MovieA = new ArrayList<Movie>();

	
	public void showAllMovies() {
		//--------------------------------------------------------
		// Summary: purpose of this method is to show all movies  that exist in the movie Tree
		// Precondition: all movies exist in the movieTree
		// Postcondition: we obtained all movies from the movieTree and printed all datas
		//--------------------------------------------------------
		
		if (!MovieTree.isEmpty()) {
			// if movie tree is not empty we are printing values of every node
			MovieTree.printNodes(MovieTree.getRoot());

		} else {
			// to raise an information message if there is no data in the movieTree
			System.out.println("NONE");
		}
		System.out.println();
	}
	
	
	public void addMovie(String t, String df, String dl, int day1, int month1, int year1) {
		//--------------------------------------------------------
		// Summary: purpose of this method is adding to given movie to the movie tree
		// Precondition: given movie does not exist in the movieTree
		// Postcondition: we added the movie to the movie tree acording to its year as key and others as value.
		// Also, if the given movie exists in the tree, we are overriding its datas
		//--------------------------------------------------------
		
		
		// to find all nodes recuresively
		MovieTree.findFullNode(MovieTree.getRoot());

		// in order to create a movie object with given arguments 
		Movie movie = new Movie(t, df, dl, day1, month1, year1);

		// to check wheter movie already  exist in the movie tree or not 
		boolean repCheck = false;

		if (check(t)) {
			// we are checking if the given movie is exist in the tree 
			
			MovieTree.put(year1, movie);
			// if it does not exist, means that no need to override operation,
			// we are putting the movie object that we created to the movie tree 
			valA.add(t);
			keyA.add(year1);
			// to print an ifnoermation message about  movie  that added
			MovieA.add(movie);
			System.out.println("INFO: Movie " + t + " has been added");
		} else {
			// if we need to make overriding operation,
			// we are deleting oldest one from the tree first.
			
			for (int i = 0; i < valA.size(); i++) {
				// in order to do that we are comparing give  title with movies that already exists in the tree
				if (valA.get(i).equals(t)) {

					int key = keyA.get(i);
					
					//then we are finding accurate movie with Specget function with giving  both movies's key and values as an argument.
					// with this method we are returning proper data from the tree and assigned it to movie object 'toComp'
					
					Movie toComp = (MovieDatabase.Movie) MovieTree.Specget(key, MovieA.get(i));

					if (t.equals(toComp.title)) {
						
						// if assigned object's title is equal to given title we are removing older data from the tree 
						deleteMovie(t);
						valA.set(i, "null");
						// to add latest data to the tree we set repcheck variable to true 
						repCheck = true;

					}
				}

			}

		}
		if (repCheck) {
			// if repcheck is true it means that we deleted the older data and we need to add newest data of the movie to the tree
			// so we are putting it's value and key to the Movie tree
			MovieTree.put(year1, movie);
			// note that, reasons that   we delete older and added latests is that, if we changed data of the movie (lets consider year) in the older node
			// suppose 1991 to 2000 , since its also key value we will ruin the order int the tree, thats why we need to opearations(remove older add latest)
			valA.add(t);
			keyA.add(year1);
			// We are priting an informeation message to the user 
			MovieA.add(movie);
			System.out.println("INFO: Movie " + t + " overwritten");
		}

		System.out.println();

	}
	
	public void removeMovie(String movieTitle) {
		//--------------------------------------------------------
		// Summary: purpose of this method is removing to given movie to the movie tree
		// Precondition: given movie exist in the movieTree
		// Postcondition: we removed the movie to the movie tree acording to its year as key and others as value.
		//--------------------------------------------------------
				
		String title = movieTitle;

		// to check wheter the given movie exists or not
		boolean check = false;
		for (int i = 0; i < valA.size(); i++) {
			if (valA.get(i).equals(title)) {

				// if it exist we set check variable to true 
				check = true;
				int key = keyA.get(i);
				//then we are finding accurate movie with Specget function with giving  both movies's key and values as an argument.
				// with this method we are returning proper data from the tree and assigned it to movie object 'toComp'
				Movie toComp = (MovieDatabase.Movie) MovieTree.Specget(key, MovieA.get(i));

				if (title.equals(toComp.title)) {
					// if assigned object's title is equal to given title we are removing  movie from the Movie treetree acording to its key
					
					deleteMovie(title);
					// to print an information message to the user
					valA.set(i, "null");
					System.out.println("INFO: Movie " + title + " has been removed");
				}
			}

		}
		if (!check) {
			// if it does not exist we are printing an error message to the user 
			System.out.println("Movie does not exist");
		}
		System.out.println();

	}
	
	
	
	public void addActor(String M, String F, String L, String R) {
		//--------------------------------------------------------
		// Summary: purpose of this method is to put actors to bst that the accurate movie has.
		// Precondition: given actor does not exist on the actor tree of the given movie
		// Postcondition: Given actor added to actor tree
		//--------------------------------------------------------

		String Mname = M;
		String Fname = F;
		String Lname = L;
		String Role = R;

		Actor act = new Actor(Mname, Fname, Lname, Role);

		// to check given movie exist in the movie tree
		boolean exist = false;
		for (int i = 0; i < MovieA.size(); i++) {

			if (valA.get(i).equals(Mname) && !valA.get(i).equals("null")) {

				String fullName = F + " " + L;

				// if it exists we are putting actor to our actor tree within the movie object
				MovieA.get(i).ActorTree.put(fullName, act);
				System.out.println("INFO: " + fullName + " has been added to the movie " + Mname);

				exist = true;

			}

		}
		// to raise an error message if movie does not exist
		if (!exist) {
			System.out.println("INFO: Movie " + Mname + " does not exist.");
		}
		System.out.println();

	}
	public void showMovie(String movieTitle) {
		//--------------------------------------------------------
		// Summary: purpose of this method is to show all information about given movie 
		// Precondition: given movie exist in the movieTree
		// Postcondition: we obtained the value of the given movie from the movieTree and print all relevant data
		//--------------------------------------------------------
		String title = movieTitle;
		// to check wheter the given movie exists or not
		boolean check = false;

		for (int i = 0; i < valA.size(); i++) {
			if (valA.get(i).equals(title)) {
				// if it exist we set check variable to true 
				check = true;
				int key = keyA.get(i);
				//then we are finding accurate movie with 'Specget' function with giving  both movies's key and values as an argument.
				// with this method we are returning proper data from the tree and assigned it to movie object 'toComp'
				Movie toComp = (MovieDatabase.Movie) MovieTree.Specget(key, MovieA.get(i));

				if (title.equals(toComp.title)) {
					// if assigned object's title is equal to given title we are removing  movie from the Movie treetree acording to its key
					System.out.println(toComp.title);
					System.out.println(toComp.Day + "/" + toComp.Month + "/" + toComp.Year);
					System.out.println(toComp.directorF + " " + toComp.directorL);
					
					// after printing all data from the tree we are traviling on ActorTree within the proper node in the movieTree,
					//in order to print actors in the movie
					
					
				if (toComp.ActorTree.isEmpty()) {
					// if actorTree in the movie does not have any node yet.
						System.out.println("--none--");
					} else {
						// if actor tree in the movie object is not empty, 
						// we are printing all nodes in the actor tree of the movie 
						toComp.ActorTree.printNodes(toComp.ActorTree.getRoot());
					}

				}
			}

		}

		if (!check) {
			// if it does not exist we are printing an error message to the user 
			System.out.println("Movie does not exist.");
		}
		System.out.println();
	}

	public void trying() {
		// purpose of this method is traviling on the nodes of current tree via inputs("L","R")
		// which also means we are checking tree with this method to understand if there is an error in the methods or not.
		MovieTree.trytree();;
	}

	// 
	public void showDirectorMovies(String directorFirstName, String directorLastName) {
		//--------------------------------------------------------
		// Summary: purpose of this function is to show movies that belong to a diretor
		// Precondition: director exists in data of several movies 
		// Postcondition: with the query we checked every node in the tree and printed the data of node if it's director is given director
		//--------------------------------------------------------
		
		
		String FirstName = directorFirstName;
		String LastName = directorLastName;
		String FullName = FirstName + " " + LastName;
		ArrayList<String> temp = new ArrayList<String>();

		System.out.println(FullName);

		// to get directors movies from tree
		// Since desc method returns an arraylist we are getting all proper movie data
		// in the given list.
		MovieTree.desc(MovieTree.getRoot(), temp);
		for (String a : temp) {

			// to split the movie data into unique parts in order to compare it

			String movieArray[] = a.split(", ");

			String Director = movieArray[2];

			String keyone = movieArray[1];

			String movieFirst = movieArray[0];

			// to find proper movie
			for (int i = 0; i < MovieA.size(); i++) {
				if (valA.get(i) == "null") {
					continue;
				}

				// in order to find movie objeet to compare it with the movies in the tree
				Movie current = MovieA.get(i);
				if (current.Year == Integer.parseInt(keyone) && current.directorL.equals(directorLastName)) {

					// if all conditision are satisfied we are returning the accurate movie from
					// tree
					// we are using Specget function in the bst class in order to return value of
					// the intended movie
					if (current.title.equals(movieFirst)) {
						String movie = MovieA.get(i).toString();
						if (movie.equals(a)) {
							Movie found = (MovieDatabase.Movie) MovieTree.Specget(keyA.get(i), MovieA.get(i));
							System.out.println(found.title + ", " + found.Day + "/" + found.Month + "/" + found.Year);
						}
					}
				}

			}

		}
		System.out.println();

	}

	
	public void showMovies(int year) {
		//--------------------------------------------------------
		// Summary: purpose of this method is showing movies in the given year
		// Precondition: movies exist in three  according to year's as keys
		// Postcondition: we get data of all values from tree with desc funciton in descending order and printed movies that released in the given year 
		//--------------------------------------------------------
		
		int key = year;
		ArrayList<String> temp = new ArrayList<String>();

		// Since desc funciton in the tree returns an arraylist we are getting all
		// proper values in the given arraylist
		MovieTree.desc(MovieTree.getRoot(), temp);

		System.out.println(key);

		for (String movie : temp) {

			// to split the movie data into unique parts in order to compare it

			String movieArray[] = movie.split(", ");

			String Director = movieArray[2];

			String keyone = movieArray[1];

			String movieFirst = movieArray[0];

			if (key == Integer.parseInt(keyone)) {

				for (int i = 0; i < MovieA.size(); i++) {

					// if all conditision are satisfied we are returning the accurate movie from tree
					 
					// we are printing the splitted data that obtained from the tree via desc function with string operations
					
					Movie current = MovieA.get(i);
					String curDirector = current.directorF + " " + current.directorL;


					if (curDirector.equals(Director) && current.Year == Integer.parseInt(keyone)
							&& current.title.equals(movieFirst)) {
						System.out.println(current.title + ", " + current.Day + "/" + current.Month);
					}
				}

			}

		}
		System.out.println();

	}

	public void showMovies(int year1, int year2) {
				//--------------------------------------------------------
				// Summary: purpose of this method is showing movies between the given years
				// Precondition: movies exist in three  according to year's as keys
				// Postcondition: we get data of all values from tree with desc funciton in descending order and printed movies that released between the given years 
				//--------------------------------------------------------

		int key1 = year1;
		int key2 = year2;
		ArrayList<String> temp = new ArrayList<String>();
		
		// Since desc funciton in the tree returns an arraylist we are getting all
		
		// proper values in the given arraylist
		MovieTree.asc(MovieTree.getRoot(), temp);

		System.out.println(year1 + "-" + year2);

		for (String movie : temp) {

			String movieArray[] = movie.split(", ");

			String Director = movieArray[2];

			String keyone = movieArray[1];

			String movieFirst = movieArray[0];
			// to split the movie data into unique parts in order to compare it
			
			if (key1 <= Integer.parseInt(keyone) && Integer.parseInt(keyone) <= key2) {

				for (int i = 0; i < MovieA.size(); i++) {
					// if all conditision are satisfied we are returning the accurate movie from tree
					 
					// we are printing the splitted data that obtained from the tree via desc function with string operations
					Movie current = MovieA.get(i);
					String curDirector = current.directorF + " " + current.directorL;
					if (curDirector.equals(Director) && current.Year == Integer.parseInt(keyone)
							&& current.title.equals(movieFirst)) {
						System.out.println(current.title + ", " + current.Year);
					}
				}

			}

		}
		System.out.println();

	}

	public void showActorRoles(String actorFirstName, String actorLastName) {
		//--------------------------------------------------------
		// Summary: purpose of this method is showing data of movies that includes given actor in it's actorTree
		// Precondition: given actor exists in several movies within the actorTree
		// Postcondition: we traveled on every possible trees and checked wheter actorTree of movie contains actor or not.
		// If it contains, we printed the data of movie that satisfied the conditions
		//--------------------------------------------------------

		String First = actorFirstName;
		String Last = actorLastName;
		String FullName = First + " " + Last;
		System.out.println(actorFirstName + " " + actorLastName);
		ArrayList<String> temp = new ArrayList<String>();
		// Since desc funciton in the tree returns an arraylist we are getting all
		// proper values in the given arraylist
		MovieTree.desc(MovieTree.getRoot(), temp);
		
		// we obtained data from the main tree and we are finding proper movies in the list that returned from the via for loops.
		for (String a : temp) {
			for (int i = 0; i < MovieA.size(); i++) {
				if (MovieA.get(i).toString().equals(a)) {

					if (MovieTree.Specget(keyA.get(i), MovieA.get(i)) != null) {
						
						// we get the data of movie that satisfied the conditions via specget function by giving key and value of the movie as parameter.
						Movie movie = (MovieDatabase.Movie) MovieTree.Specget(keyA.get(i), MovieA.get(i));
						ArrayList<String> forMovie = new ArrayList<String>();
						
						movie.ActorTree.desc(movie.ActorTree.getRoot(), forMovie);
						// Since desc funciton in the tree returns an arraylist we are getting all
						// proper values in the given arraylist
						// note that, different from the operation that we did beggining of the method,
						//we are getting value in descending order from ACTOR TREE of the movie that satisfied the conditions 
						for (String b : forMovie) {
							String nameArray[] = b.split(",");
							if (nameArray[0].equals(FullName)) {
								// if movie contains the actor we are priting its data 
								System.out.println(nameArray[1] + ", " + movie.title + ", " + movie.Year);
							}
						}

					}
				}
			}
		}
		System.out.println();

	}

	

	public void removeActor(String movieTitle, String actorFirstName, String actorLastName, String role) {
		
		//--------------------------------------------------------
		// Summary: purpose of this method is removing the actor from actor tree of the movie that has the given title
		// Precondition: given actor exists in the given movie's actorTree
		// Postcondition: we traveled on every possible trees and checked wheter actorTree of movie contains actor.
		// If it contains we are removiging actor from actorTree via delete methed.
		//--------------------------------------------------------

		String Mname = movieTitle;
		String Fname = actorFirstName;
		String Lname = actorLastName;
		boolean exist = false;
		
		// required string operations in order to compare titles
		// exist variable is used for checking wheter the given movie exists or not
		// if it does not exist in the main tree(Movie Tree) we are priting an error message
		for (int i = 0; i < MovieA.size(); i++) {
			
			// Since full name of the actor is kept as key in the tree,
			// if movie is found we are creating a string that holds full name of the tree from given input 
			if (valA.get(i).equals(Mname) && !valA.get(i).equals("null")) {

				String fullName = Fname +" "+ Lname;
				// A explained above we are deleting given name from the Actortree of the movie  with giving its full name as key 
				MovieA.get(i).ActorTree.delete(fullName);
				System.out.println("INFO: " + Fname + " " + Lname + " has been removed from the movie " + movieTitle);
				exist = true;
				
			}

		}
		// If movie does not exist we are priting an error message
		if (!exist) {
			System.out.println("Movie" + Mname + " does not exist.");
		}
		System.out.println();

	}

	

	

	

	

	

	public boolean check(String title) {
		// to check wheter the given movie exists in the movietree
		boolean check = true;
		for (int i = 0; i < valA.size(); i++) {
			if (valA.get(i).equals(title)) {
				check = false;
			}
		}
		return check;
	}
	
	// Note that it is enough to use delete method in the bst class in order to satisfy cases
	// that written in the given pdf about hw3
	// However, if an input will be given in order to remove a movie that share its key with other movies in the tree
	// delete method wont work efficently. Hence we developed this algorithm 
	public void deleteMovie(String movieTitle) {
		
		//--------------------------------------------------------
		// Summary: purpose of this method is removing the node from  tree that has the given title
		// Precondition: given movie exist on the movie tree 
		// Postcondition: we removed  movie that we want to delete from the tree  
		//--------------------------------------------------------
		
		String title = movieTitle;
		
		
		for(int i=0;i<valA.size();i++) {
			
			if(valA.get(i).equals(title)) {
				Movie current = MovieA.get(i);
				int key = keyA.get(i);
				// to give key value and newKey as an argument.
				MovieTree.SpecDelete(key, current, key+1);
				
				
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	

}
