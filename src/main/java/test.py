import requests
import json
import concurrent.futures
import time
import sys

headers = {
    'x-api-key': "b0979afd-2ce3-4786-af62-ab53f88204ff",
    'content-type': "application/json"
}

def log_writer(my_string):
    if True:
        log_file.write(str(time.time()) + my_string + '\n')

MAX_THREAD_COUNT = 15
submit_count = 0

def getNupdate(deviceId, is_log):
    global submit_count
    id = deviceId.rstrip('\n')
    try:
        deviceUrl = "http://10.33.109.245:8000/v1/apps/retail/devices/" + id
        response = requests.get(deviceUrl)
        if(response.ok):
            jData = json.loads(response.content)
            appVersion = jData["data"]["appVersion"]
            data = {}
            data['appVersion'] = appVersion
            payload = json.dumps(data)
            connekt_url = "http://10.47.0.120/v1/registration/push/unknown/RetailApp/"+ id
            cnktResponse = requests.request("PATCH", connekt_url, data=payload, headers=headers)
            if int(time.time()) % 600 == 0:
                log_writer("Ingestion completed till : " + id)
            if not (cnktResponse.ok or cnktResponse.status_code == 404):
                log_writer("Connekt failed : " + id + "\t\t\t : " + str(cnktResponse.status_code))
        else:
            log_writer("device failed : " + id + "\t\t\t : " + str(response.status_code))
        submit_count -= 1
    except:
        log_writer("Exception"+sys.exc_info()[0]+ "for : "+id)

def main():
    global log_file
    log_file = open('out.txt','w')
    log_file.truncate()
    global submit_count
    total_count = 0
    executor = concurrent.futures.ThreadPoolExecutor(max_workers = MAX_THREAD_COUNT)
    prev_time = 0
    for deviceId in open('ids.txt'):
        while submit_count > 2 * MAX_THREAD_COUNT:
            time.sleep(0.01)

        current_time = int(time.time())
        if current_time != prev_time and current_time % 10 == 0 :
            print ("Total Count:" + str(total_count) + " Queue Count:" + str(submit_count) + " current time:" + str(current_time))
            total_count = 0
            do_log = True
            prev_time = current_time
        else:
            do_log = False

        total_count += 1
        submit_count += 1
        executor.submit(getNupdate, deviceId, do_log)
    log_file.close()

main()