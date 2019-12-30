CC= g++ 
CXXFLAGS= -Wall -ansi --pedantic -g

CPP_O_FILE = analyzer.o main.o

all: $(CPP_O_FILE)
	$(CC) $(CXXFLAGS) -o arraylist_analysis $(CPP_O_FILE)

clean:
	rm -rf *.o
	rm -rf *~
	rm -rf arraylist_analysis


%.o: %.cpp
	$(CC) $(CPPFLAGS) -c $< -o $@
