import java.util.*;

class Film {

    String name;
    double rating;

    Film(String name,double rating){
        this.name=name;
        this.rating=rating;
    }

    public String toString(){
        return "Movie : "+name+"\nRating : "+rating+"\n";
    }
}

public class MovieRecommendationDSA {

static HashMap<String,HashMap<String,ArrayList<Film>>> moviesData=new HashMap<>();

static LinkedList<Film> watchlist=new LinkedList<>();

static Queue<Film> recentlyViewed=new LinkedList<>();

static PriorityQueue<Film> trendingMovies=
new PriorityQueue<>((a,b)->Double.compare(b.rating,a.rating));

static Scanner sc=new Scanner(System.in);

static void loadMovies(){

HashMap<String,ArrayList<Film>> hindi=new HashMap<>();

hindi.put("Action",new ArrayList<>(Arrays.asList(
new Film("Animal",4.3),
new Film("Pathaan",4.1),
new Film("KGF Chapter 2",4.5),
new Film("Dhoom 2",4.5),
new Film("Tiger 3",4.5)
)));

hindi.put("Romance",new ArrayList<>(Arrays.asList(
new Film("Saiyaara",4.8),
new Film("Aashiqui 2",4.3),
new Film("Laila Majnu",4.1),
new Film("Kuch Kuch Hota Hai",4.1),
new Film("Student of the Year",4.1)
)));

hindi.put("Comedy",new ArrayList<>(Arrays.asList(
new Film("3 Idiots",4.9),
new Film("Housefull",4.0),
new Film("Ajab Prem Ki Ghazab Kahani",4.2),
new Film("Munna Bhai MBBS",4.0),
new Film("Welcome",4.0)
)));

hindi.put("Thriller",new ArrayList<>(Arrays.asList(
new Film("Vikram Vedha",4.6),
new Film("Andhadhun",4.7),
new Film("Chup",4.2),
new Film("Drishyam",4.2),
new Film("Shaitaan",4.2)
)));

hindi.put("Horror",new ArrayList<>(Arrays.asList(
new Film("Tumbbad",4.4),
new Film("Raaz",4.0),
new Film("Bhoot",3.9),
new Film("Bhool Bhulaiyaa 2",3.9),
new Film("Laxmii",3.9)
)));

moviesData.put("Hindi",hindi);


HashMap<String,ArrayList<Film>> english=new HashMap<>();

english.put("Action",new ArrayList<>(Arrays.asList(
new Film("Avengers Endgame",4.8),
new Film("John Wick",4.5),
new Film("Mad Max Fury Road",4.4),
new Film("Mission Impossible",4.4),
new Film("The Batman",4.4)
)));

english.put("Romance",new ArrayList<>(Arrays.asList(
new Film("Titanic",4.7),
new Film("La La Land",4.3),
new Film("Beauty and the Beast",4.4),
new Film("Me Before You",4.4),
new Film("My Fault",4.4)
)));

english.put("Comedy",new ArrayList<>(Arrays.asList(
new Film("Hangover",4.2),
new Film("Superbad",4.1),
new Film("Home Alone",4.6),
new Film("Ted",4.4),
new Film("Scary Movie",4.4)
)));

english.put("Thriller",new ArrayList<>(Arrays.asList(
new Film("Inception",4.9),
new Film("Se7en",4.6),
new Film("Shutter Island",4.5),
new Film("Escape Room",4.4),
new Film("Prisoners",4.4)
)));

english.put("Horror",new ArrayList<>(Arrays.asList(
new Film("Conjuring",4.5),
new Film("IT",4.2),
new Film("The Nun",3.9),
new Film("Annabelle",4.4),
new Film("The Witch",4.4)
)));

moviesData.put("English",english);


HashMap<String,ArrayList<Film>> telugu=new HashMap<>();

telugu.put("Action",new ArrayList<>(Arrays.asList(
new Film("RRR",4.9),
new Film("Pushpa",4.4),
new Film("Saripodhaa Sanivaaram",4.5),
new Film("Aravinda Sametha",4.5),
new Film("Saaho",4.5)
)));

telugu.put("Romance",new ArrayList<>(Arrays.asList(
new Film("Geetha Govindam",4.3),
new Film("Sita Ramam",4.1),
new Film("Fidaa",4.6),
new Film("Rang De",4.6),
new Film("Thandel",4.6)
)));

telugu.put("Comedy",new ArrayList<>(Arrays.asList(
new Film("F2",4.0),
new Film("Jathi Ratnalu",4.3),
new Film("DJ Tillu",4.2),
new Film("Sankrantiki Vastunam",4.6),
new Film("Om Bheem Bush",4.6)
)));

telugu.put("Thriller",new ArrayList<>(Arrays.asList(
new Film("Rakshasudu",4.3),
new Film("Hit 3",4.2),
new Film("Goodachari",4.4),
new Film("Yashoda",4.4),
new Film("Mathu Vadalara",4.4)
)));

telugu.put("Horror",new ArrayList<>(Arrays.asList(
new Film("Arundhati",4.5),
new Film("Rajasaab",4.0),
new Film("Virupaksha",4.1),
new Film("Geetanjali",4.1),
new Film("Masooda",4.3)
)));

moviesData.put("Telugu",telugu);


for(String lang:moviesData.keySet()){
for(String genre:moviesData.get(lang).keySet()){
for(Film m:moviesData.get(lang).get(genre)){
trendingMovies.add(m);
}
}
}
}

static void exploreMovies(){

System.out.println("\nAvailable Languages:\n");

for(String lang : moviesData.keySet()){
    System.out.println(lang);
}

System.out.println("\nEnter your language:");
String lang=sc.nextLine();

if(!moviesData.containsKey(lang)){
    System.out.println("Invalid language");
    return;
}

System.out.println("\nAvailable Genres:\n");

for(String genre : moviesData.get(lang).keySet()){
    System.out.println(genre);
}

System.out.println("\nEnter your genre:");
String genre=sc.nextLine();

if(!moviesData.get(lang).containsKey(genre)){
    System.out.println("Invalid genre");
    return;
}

ArrayList<Film> list=moviesData.get(lang).get(genre);

System.out.println("\nMovies Available:\n");

for(Film m:list){
    System.out.println(m);
}
}


static void addToWatchlist(){

System.out.println("\nEnter movie name to add:");
String name=sc.nextLine();

for(String lang:moviesData.keySet()){
for(String genre:moviesData.get(lang).keySet()){
for(Film m:moviesData.get(lang).get(genre)){

if(m.name.equalsIgnoreCase(name)){

watchlist.add(m);

recentlyViewed.add(m);

if(recentlyViewed.size()>5)
recentlyViewed.poll();

System.out.println("\nMovie added to watchlist\n");
return;
}
}
}
}

System.out.println("Movie not found");
}

static void showWatchlist(){

System.out.println("\nYour Watchlist:\n");

for(Film m:watchlist){
System.out.println(m);
}
}

static void showRecentlyViewed(){

System.out.println("\nRecently Viewed Movies:\n");

for(Film m:recentlyViewed){
System.out.println(m);
}
}

static void showTrending(){

System.out.println("\nTrending Movies:\n");

PriorityQueue<Film> temp=new PriorityQueue<>(trendingMovies);

int count=0;

while(!temp.isEmpty()&&count<5){
System.out.println(temp.poll());
count++;
}
}

static void sortMovies(){

ArrayList<Film> all=new ArrayList<>();

for(String lang:moviesData.keySet()){
for(String genre:moviesData.get(lang).keySet()){
all.addAll(moviesData.get(lang).get(genre));
}
}

for(int i=1;i<all.size();i++){

Film key=all.get(i);
int j=i-1;

while(j>=0 && all.get(j).rating<key.rating){
all.set(j+1,all.get(j));
j--;
}

all.set(j+1,key);
}

System.out.println("\nMovies Sorted by Rating:\n");

for(Film m:all){
System.out.println(m);
}
}

public static void main(String[] args){

loadMovies();

while(true){

System.out.println("\n===== CINEMA INSIGHT SYSTEM =====\n");

System.out.println("1 Explore Movies");
System.out.println("2 Add Movie to Watchlist");
System.out.println("3 View Watchlist");
System.out.println("4 Recently Viewed Movies");
System.out.println("5 Trending Movies");
System.out.println("6 Sort Movies by Rating");
System.out.println("7 Exit");

System.out.println("\nEnter your choice:");

int choice=sc.nextInt();
sc.nextLine();

switch(choice){

case 1: exploreMovies(); break;
case 2: addToWatchlist(); break;
case 3: showWatchlist(); break;
case 4: showRecentlyViewed(); break;
case 5: showTrending(); break;
case 6: sortMovies(); break;
case 7: System.out.println("\nThank you for using Cinema Insight System! Enjoy your movies \n");
        System.exit(0);

}
}
}
}