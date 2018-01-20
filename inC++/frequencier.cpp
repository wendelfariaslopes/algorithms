#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <cstdlib>

typedef char *mot;
typedef char *texte;

using namespace std;

bool ecrireFichier(char*);
bool lireFichier(char*);
bool lireFichierCaractere(char* pUrl);

bool comparerMots(char* mot1, char* mot2);

void ajouterMot(vector<string> &v, char* mot);
void listeMots(vector<string> &v);
void listeMots(vector<string> &v, vector<int> &index);

void rechercheMot(string, string);
void rechercheMot(string, string, int);

vector<string> splitString(const char*, char);
vector<string> creerRepertoire(char *);
vector<string> creerRepertoire(char *texte, vector<string> neophyte, vector<int> index);

int main(int argc, char *argv[]) {

	//char texte[] = "wendel farias lopes wendel car wendel farias lopes lopes";
	//vector<string> v;
	//vector<string> neophyte; // apprentice, learner, tyro, a beginner or novice
	//vector<int>	index;
	//v = splitString(texte, ' ');
	//neophyte.push_back(v[0]);

	//char texte2[] = "carlos farias figueirs wendel car wendel farias lopes lopes";

	//v =	creerRepertoire(texte);

	//listeMots(v);

	//listeMots(neophyte);

//	for(int i = 0; i < argc; i++){
//		cout << i << " -- "<< argv[i] << endl;
//	}

//	char texte[] = "";
//
//
//	vector<string> repertory;
//
//
//	char mot[]= "BTA";
//	char mot2[] = "BHG";
//	char mot3[] = "MAMA";
//
//	vector<string> v;
//	v.push_back(mot);
//	v.push_back(mot2);
//	v.push_back(mot3);
//
//	for(int i =0; i <3 ; i++){
//		cout << i <<") "<< v[i] << endl;
//		rechercheMot(texte, v[i]);
//	}

//	clock_t inicio2 = clock();
//
//		cout << texte << '\n';
//		int i = 0;
//		rechercheMot(texte, mot,i);
//
//		clock_t total2 = clock() - inicio2;
//
//	clock_t inicio = clock();
//
//	cout << texte << '\n';
//	rechercheMot(texte, mot);
//
//	clock_t total = clock() - inicio;
//
//
//
//
//	cout << "Tempo para a funcao with replace   : "<< double(total)/CLOCKS_PER_SEC << " segundos." << endl;
//	cout << "Tempo para a funcao without replace: "<< double(total2)/CLOCKS_PER_SEC << " segundos." << endl;
//	cout << "Ratio f1/f2 = " << ((double(total)/double(total2))-1) * 100 << "%"<<endl;

//	for(int i=0; i < 2; i++){
//		vec.push_back(vecA[i]);
//	}
//
//	listeMots(vec);

//	char myName1[] = "text.txt";
//	char myName2[] = "texttxt";
//
//	comparerMots(myName1, myName2);

//	string myName = "John";
//
//	cout << "Strings" << endl;
//	cout << "Padrão: " << myName << endl;
//	cout << setw(35) << right << "Com setw(35) e \"right\": " << myName << endl;
//	cout.width(20);
//	cout << "Com width(20): " << myName << endl;
//	cout << endl;

	char url[] = "text1.txt";

//	bool ecrit = ecrireFichier(url);
//	if(ecrit){
//		cout << "Le fichier a été écrit.\n";
//	}
//
	bool lu = lireFichier(url);
	if(lu){
		cout << "******* FIN ********";
	}

   return 0;
}

bool
ecrireFichier(char* pUrl){

	bool situation = false;
	string data;			//criamos um array de 80 caracteres
	ofstream outfile;		//criamos objeto da classe ofstream

	outfile.open(pUrl);	//chamamos a função membro da classe para o objeto criado.
	                    // Esta função membro cria o arquivo "joao.doc"

	if (outfile.is_open() && outfile.good()) {
		cout << "\n";
		cout << "Debut de Ecriture" << endl;
		cout << "Informer quelque choise: \n";

		getline(cin, data);
		outfile << data << endl;
		cout << "informe numero: ";
		cin >> data;
		cin.ignore();
		outfile << data << endl;
		outfile.close();
	    situation = true;
	}
	return situation;
}

bool
lireFichier(char* pUrl){

	bool situation = false;
	string ligne;
	ifstream infile;
	infile.open(pUrl);
	char *c;
	vector<string> vecLigne;
	vector<string> vec;

	if (infile.is_open() && infile.good()){
	    getline(infile, ligne);
	    while(!infile.fail()){
	       cout << ligne << endl;
	       c = new char[ligne.size() + 1];
	       c = strdup(ligne.c_str());
	       vecLigne = creerRepertoire(c);
	       vec.insert(vec.end(),vecLigne.begin(),vecLigne.end());
	       getline(infile, ligne);
	    }
	    infile.close();
	    situation = true;
	 }

	listeMots(vec);

	return situation;
}

bool
lireFichierCaractere(char* pUrl){

	bool situation = false;
	ifstream txtFile;
	txtFile.open(pUrl);
	char c;

	if (txtFile.is_open() && txtFile.good()){
	    while(txtFile.get(c)){ //
	       cout << c << endl;
	    }
	    txtFile.close();
	    situation = true;
	 }

	return situation;
}

vector<string>
splitString(const char *str, char c) {

    vector<string> result;

    do{
        const char *begin = str;

        while((!isspace(*str)
        		&& !ispunct(*str)
				&& !iscntrl(*str)
				&& *str)){
            str++;
        }
        result.push_back(string(begin, str));
    } while (0 != *str++);

    return result;
}


void
listeMots(vector<string> &v){
	for(int i = 0; i < v.size() ; i++){
		cout << i << ")          " << v[i] << "\n";
	}
}

void
listeMots(vector<string> &v, vector<int> &index){
	for(int i = 0; i < v.size() ; i++){
		cout << "          " << index[i] << " " << v[i] << "\n";
	}
}

void
rechercheMot(string texte, string mot){

	int sizeWordFound = mot.length();

	size_t found = texte.find(mot);
	if (found!=std::string::npos){
		//cout <<  mot   <<"' found at: " << found << '\n';
		texte.replace(texte.find(mot), sizeWordFound, "");
		rechercheMot(texte, mot);
	}
}

/*
 * Recherche un mot(petite chaine de caractere) dans un texte(grande chaine caractere)
 * avec recursivite.
 */

void
rechercheMot(string texte, string mot, int pos){

	//int sizeWordFound = str2.length();
	size_t found = texte.find(mot, pos);
	if (found!=std::string::npos){

		cout <<  mot   <<"' found at: " << found << '\n';
		//str.replace(str.find(str2), sizeWordFound, "");
		//cout << str << '\n';
		rechercheMot(texte, mot, found+1);
	}
}

vector<string>
creerRepertoire(char *texte){

	vector<string> v;
	vector<string> neophyte; // apprentice, learner, tyro, a beginner or novice
	vector<int>	index;
	v = splitString(texte, ' ');

	bool found = false;	// variable de controle

	for(int i = 0; i < v.size(); i++){
		//cout << "Debut de recherche pour " << v[i] << endl;
		for(int j = 0; j < neophyte.size(); j++){
			if(v[i]!=neophyte[j]){
				//cout << "diff" << endl;
				found = false;

			}else{
				//cout << "equal" << endl;
				found = true;
				index[j] = index[j] + 1;			// ajouter au index[j] une unite correspondent a la repetition de mot[j]
				break;
			}
		}
		//cout << "fini recherche pour " << v[i] << endl;
		if(!found){
			neophyte.push_back(v[i]);
			index.push_back(1);
		}
	}

	//listeMots(neophyte);
	listeMots(neophyte, index);

	return neophyte;
}

vector<string>
creerRepertoire(char *texte, vector<string> neophyte, vector<int> index){

	vector<string> v;
	//vector<string> neophyte; // apprentice, learner, tyro, a beginner or novice
	//vector<int>	index;
	v = splitString(texte, ' ');

	bool found = false;	// variable de controle

	for(int i = 0; i < v.size(); i++){
		//cout << "Debut de recherche pour " << v[i] << endl;
		for(int j = 0; j < neophyte.size(); j++){
			if(v[i]!=neophyte[j]){
				//cout << "diff" << endl;
				found = false;

			}else{
				//cout << "equal" << endl;
				found = true;
				index[j] = index[j] + 1;			// ajouter au index[j] une unite correspondent a la repetition de mot[j]
				break;
			}
		}
		//cout << "fini recherche pour " << v[i] << endl;
		if(!found){
			neophyte.push_back(v[i]);
			index.push_back(1);
		}
	}

	//listeMots(neophyte);
	//listeMots(neophyte, index);

	return neophyte;
}
