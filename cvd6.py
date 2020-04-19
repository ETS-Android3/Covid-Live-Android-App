from selenium import webdriver                   
from selenium.webdriver.common.keys import Keys 
import time
#import crypto
#import sys
#sys.modules['Crypto'] = crypto
from firebase import firebase
from selenium.webdriver.chrome.options import Options
firebase=firebase.FirebaseApplication("https://covid-live-da26d.firebaseio.com/",None)
chrome_options = Options()
chrome_options.add_argument("--headless")
from webdriver_manager.chrome import ChromeDriverManager
from selenium import webdriver
chrome_options=chrome_options
import requests
browser =  webdriver.Chrome(chrome_options=chrome_options,executable_path=ChromeDriverManager().install())
#webdriver.Chrome(chrome_options=chrome_options,executable_path='chromedriver 3')

if __name__ == '__main__':
    while(True):
            
        
        
            browser.get('https://www.covid19india.org') 
            time.sleep(2)   
           # browser.find_element_by_xpath('//*[@id="chart"]/g/g/path[70]').click()
            
            
            total_cases=(browser.find_element_by_xpath('/html/body/div/div/div/div[2]/div/div/div[1]/div[2]/div[1]/h1')).text.strip()
            total_deaths=(browser.find_element_by_xpath('/html/body/div/div/div/div[2]/div/div/div[1]/div[2]/div[4]/h1')).text.strip()
            recovered=(browser.find_element_by_xpath('/html/body/div/div/div/div[2]/div/div/div[1]/div[2]/div[3]/h1')).text.strip()
            active_cases=(browser.find_element_by_xpath('/html/body/div/div/div/div[2]/div/div/div[1]/div[2]/div[2]/h1')).text.strip()
            json_data=requests.get('https://api.covid19india.org/state_district_wise.json').json()
            mbd_cases=str(json_data['Uttar Pradesh']['districtData']['Moradabad']['confirmed'])
            
            data={'Total Cases':total_cases,
                  'Active Cases':active_cases,
                  'Moradabad Cases':mbd_cases,
                  'Total Deaths':total_deaths,
                  'Total Recovered':recovered
                  }
            
            firebase.patch("Data",data)
            print("Data Patched")
            time.sleep(900)
            #firebase.delete('')
        
            #re-run the code
            
            
            
        #/html/body/div/div/div/div[2]/div/div/div[1]/div[2]/div[2]/h1
        
        
        
    
