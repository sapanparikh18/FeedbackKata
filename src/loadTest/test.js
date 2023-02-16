import http from 'k6/http';
import {sleep} from 'k6';

export const options = {
    duration: '1m',
    vus: 10,
    thresholds: {
        http_req_failed: ['rate<0.01'], // http errors should be less than 1%
        http_req_duration: ['p(95)<500'], // 95 percent of response times must be below 500ms
    },
    ext: {
        loadimpact: {
            projectID: 3626539,
            name: "Bucharest Crafters' load test"
        }
    }
};

export default function () {
    http.get('http://scromania.westeurope.azurecontainer.io:8080/year/1996');
    sleep(0.5);
}
