
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

// The Cloud Functions for Firebase SDK to create Cloud Functions and setup triggers.
// const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const functions = require('firebase-functions');
const nodemailer = require('nodemailer');
const admin = require('firebase-admin');

const gmailEmail = functions.config().gmail.email;
const gmailPassword = functions.config().gmail.password;

const mailTransport = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: gmailEmail,
    pass: gmailPassword,
  },
});

exports.sendEmail = functions.auth.user().onCreate(user => {
  const mailOptions = {
    from: '"EpiMaps" <piedpipergeeks@gmail.com>',
    to: user.email,
  };

  mailOptions.subject = 'Hello';
  mailOptions.text = 'Welcome email';

  return mailTransport.sendMail(mailOptions)
    .then(() => console.log('New user email sent'))
    .catch((error) => console.error('Error sending email: ', error));
});

// const auth = require('firebase-auth');
admin.initializeApp();

exports.logOnCreateUser = functions.auth.user().onCreate((user) => {
  const email = user.email;
  return logOnCreateUser(email);
});

function logOnCreateUser(email) {
  console.log(email);
}
