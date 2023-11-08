
class DriverToDriveData:

    def __init__(cls):
        cls.Line = []
        cls.dates = []
        cls.driver = []
        cls.driverAndDates = {}
        cls.filepath = "/home/ec2-user/workspace/Evergreen/upper-division-cs/dsa-23au/java-dsa/pswish-natmcl/pswish-app/src/resources/DataSet_DSAau_pswish.csv"
    
    def getCSVfile(cls):
        try:
            with open (cls.filepath, "r") as cls.file:
                cls.Line = [line for line in cls.file]  # sets each line to a list
        except Exception as e:
            print("fault at getCSVfilewith error ", e)
            
    def getColumnHeader(cls):
        cls.header = cls.Line[0].split(",")
        print("Header: ", cls.header)

    def addCSVtoLists(cls):
        lineLength = cls.Line.__len__()

        cls.driverKeys = []
        i = 1
        while i < lineLength:
            cls.Linelist = cls.Line[i].split(",")

            # Date, time and Driver constructed into a key
            driver1Key = (f"{cls.Linelist[1]}_{cls.Linelist[0]}_{cls.Linelist[6].replace('P', 'Paul')}")
            cls.driverKeys.append(driver1Key)
            i+=1
     
        print("Driver keys printout :", cls.driverKeys)
        
        for key in cls.driverKeys:
            print ("Print out of each list by index enum: ",key)
    
    def addListitemsToDict(cls):
        dict_1 = {}

        # for key in cls.driverKeys:
        #     print (key)
            # value = cls.Line
            # dict_1.update({key, value[1]})
        # print (dict_1[key])
        
    #     i = 0
    #     while i < cls.dates.__len__():
    #         for drv in cls.driver:
    #             for dat in cls.dates: 
    #                 cls.driverAndDates = {drv, dat}
    #                 i +=1

        print("end of function")
        # print("Drives and drive dates", cls.driverAndDates) 


def main():
    DTDD = DriverToDriveData()
    DTDD.getCSVfile()
    DTDD.addCSVtoLists()
    DTDD.getColumnHeader()
    DTDD.addListitemsToDict()
    

main()