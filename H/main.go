package main

import (
	"os"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/controller"
)

func main() {
	// Get args
	args := os.Args[1:]
	// new Controller
	controller := controller.Controller{Args: args}
	// Process input
	controller.UserInputControl()
}
