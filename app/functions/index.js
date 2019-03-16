const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

// The Cloud Functions for Firebase SDK to create Cloud Functions and setup triggers.
// const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
// const auth = require('firebase-auth');
admin.initializeApp();

exports.logOnCreateUser = functions.auth.user().onCreate((user) => {
  const email = user.email;
  return logOnCreateUser(email);
});

function logOnCreateUser(email) {
  console.log(email);
}
