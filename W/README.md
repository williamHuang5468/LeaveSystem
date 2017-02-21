# LeaveSystem

## Function

    takeleave add <name> <time-from> <time-end>

    takeleave list <name>

| leaveId | time-from | time-end |
|---------|-----------|----------|
|    1    | 2016-10-01|2016-10-02|
|    2    | 2016-11-05|2016-11-08|

    takeleave listall

| name    | time-from | time-end |
|---------|-----------|----------|
| william | 2016-10-01|2016-10-02|
| john    | 2016-11-05|2016-11-08|


    takeleave delete <leaveID>

    takeleave edit <leaveID> <time-from> <time-end>

### querybyname

    takeleave querybyname <name> <condition>

supported condition

- timefrom>2016-01-01
- timeend<2020-01-01
- timerange=2000-01-01,2222-01-01

> leaveID should be `unique` in whole database.
