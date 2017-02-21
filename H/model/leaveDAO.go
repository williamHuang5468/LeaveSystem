package model

import (
	"errors"

	"gitlab.hopebaytech.com/autox/study-group/clean-code/henry/takeleave/mongo"
	"gopkg.in/mgo.v2/bson"
)

const dbName = "henrydb"
const collectionName = "leave"

// LeaveDAO type
type LeaveDAO struct{}

// CreateLeave - create leave record to DB
func (dao LeaveDAO) CreateLeave(leave *Leave) bool {
	// Get mongodb session
	session := mongo.Connect()
	defer session.Close()

	// Get Collection
	leaveCollection := session.DB(dbName).C(collectionName)

	leave.BsonID = bson.NewObjectId()

	err := leaveCollection.Insert(leave)
	if err != nil {
		return false
	}
	return true
}

// GetLeaveByID Get leave object by DB
func (dao LeaveDAO) GetLeaveByID(id string) (Leave, error) {
	// Get mongodb session
	session := mongo.Connect()
	defer session.Close()

	// Get Collection
	leaveCollection := session.DB(dbName).C(collectionName)

	var leave Leave
	err := leaveCollection.FindId(bson.ObjectIdHex(id)).One(&leave)
	if err != nil {
		return leave, errors.New("GetLeaveByID error")
	}
	return leave, nil
}

// GetLeavesByName Get leave object by name
func (dao LeaveDAO) GetLeavesByName(name string) []Leave {
	// Get mongodb session
	session := mongo.Connect()
	defer session.Close()

	// Get Collection
	leaveCollection := session.DB(dbName).C(collectionName)

	// Get record
	var leaves []Leave
	_ = leaveCollection.Find(bson.M{"name": name}).Sort("timefrom").All(&leaves)
	return leaves
}

// GetLeaves Get all leaves
func (dao LeaveDAO) GetLeaves() []Leave {
	// Get mongodb session
	session := mongo.Connect()
	defer session.Close()

	// Get Collection
	leaveCollection := session.DB(dbName).C(collectionName)

	// Get records
	var leaves []Leave
	_ = leaveCollection.Find(nil).Sort("name", "timefrom").All(&leaves)
	return leaves
}

// UpdateLeave update leave record to DB
func (dao LeaveDAO) UpdateLeave(leave Leave) bool {
	// Get mongodb session
	session := mongo.Connect()
	defer session.Close()

	// Get Leave Collection
	leaveCollection := session.DB(dbName).C(collectionName)

	// Changes
	leaveUpdate := bson.M{"$set": bson.M{"timefrom": leave.TimeFrom, "timeend": leave.TimeEnd}}

	// Update record
	err := leaveCollection.UpdateId(leave.GetBsonID(), leaveUpdate)
	if err != nil {
		return false
	}
	return true
}

// DeleteLeave delete leave record from DB
func (dao LeaveDAO) DeleteLeave(id string) bool {
	// Get mongodb session
	session := mongo.Connect()
	defer session.Close()

	// Get Leave Collection
	leaveCollection := session.DB(dbName).C(collectionName)

	// Remove record
	err := leaveCollection.RemoveId(bson.ObjectIdHex(id))
	if err != nil {
		return false
	}
	return true
}
