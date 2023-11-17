#!/usr/bin/env python3
# Fix amounts in QuickBooks export CSV format
# preprocess [input_filename] [output_filename] [account_bank] [account_name] [account_owner]

import datetime
import sys
import csv
import json

input_fn = sys.argv[1]
output_fn = sys.argv[2]
acct_bank = sys.argv[3]
acct_name = sys.argv[4]
acct_owner = sys.argv[5]

with open(input_fn, "r") as input_f:
    with open(output_fn, "w+") as output_f:

        if (input_fn[-3:] != 'csv'):
            print("Not a CSV, skipping")
            print(f"  {input_fn}")
            sys.exit()

        r = csv.reader(input_f)

        input_lines = list(r)

# Field List
# 1  2        3    4      5    6    7  8              9            10           11           12       
# ID,Datetime,Type,Status,Note,From,To,Amount (total),Amount (tip),Amount (tax),Amount (fee),Tax Rate,
# 13         14             15          16                17             18                    
# Tax Exempt,Funding Source,Destination,Beginning Balance,Ending Balance,Statement Period Venmo Fees,
# 19                20                      21
# Terminal Location,Year to Date Venmo Fees,Disclaimer

        # Skip the fields line at the top, but print it out first
        output_f.write(','.join(input_lines[2]))
        
        # Venmo CSVs contain a lot of non-CSV extra lines, which are appreciated for elsewhere
        for tokens in input_lines[4:-36]: 
            # strip the leading and trailing quotes
            assert(len(tokens)>=22 and len(tokens)<=24)

            amount = tokens[8]
            negative = ""
            if (len(amount) > 0):
                if (amount[0] == '-'):
                    negative = "-"
                    amount = amount[1:]
                elif (amount[0] == '+'):
                    amount = amount[1:]
                amount = amount.lstrip() # Venmo puts a space before $
                if (amount[0] == '$'):
                    amount = negative + amount[1:]
            print(f"amount {amount}")
            tokens[8] = amount
  
            dt_object = None
            try: 
                dt_object = datetime.datetime.strptime(tokens[2], '%Y-%m-%dT%H:%M:%S')
            except:
                dt_object = datetime.datetime.strptime(tokens[2], '%Y-%m-%d')

            tokens[2] = f"{dt_object.year}-{str(dt_object.month).zfill(2)}-{str(dt_object.day).zfill(2)}"
            # Don't add a newline at the end, there's already one from before

            for (i,token) in enumerate(tokens):
                tokens[i] = tokens[i].rstrip()
            output_f.write(",".join(tokens) + "\n")
            
        output_f.flush()    
