package controller

type command interface {
	Execute()
}

// Executor - Can execute command implemented Execute method
type Executor struct{}

// Execute command
func (executor Executor) Execute(c command) {
	c.Execute()
}
