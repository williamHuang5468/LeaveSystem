package mongo

import (
	"fmt"
	"os"

	mgo "gopkg.in/mgo.v2"
)

// Connect get mongodb session
func Connect() *mgo.Session {
	session, err := mgo.Dial("127.0.0.1")
	if err != nil {
		fmt.Println("Mongo Server Not Found!")
		os.Exit(1)
	}
	return session
}
