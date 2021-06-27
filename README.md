# Shortink

Our web-application shortens long URLs. On the main page you can provide your long URL and get a short redirection URL back. These URLs are valid for 30 days.
If you create an Account, your URLs will not expire and will be displayed in your profile for future use.

As an extra feature, you can create a qr-code of your short URLs, using the following API: https://rapidapi.com/ajith/api/qr-code-generator-with-multiple-datatypes-/
**This feature is only available for account holders.**

## Authors

- [@giogreg](https://www.github.com/https://github.com/giogreg)
- [@jmancook54](https://www.github.com/https://github.com/jmancook54)

## Demo

Deployed on heroku: <https://shortink.herokuapp.com>

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`RAPIAPI_KEY`

## Running Tests

To run tests, run the following command

```bash
  gradle clean test
```
