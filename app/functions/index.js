
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
const Firestore = require('@google-cloud/firestore');
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

exports.updateRegionCount = functions.firestore.document('Hospitals/{hospitalId}/Patients/{patientId}').onCreate((snap, context) => {
  const data = snap.data();
  const disease = data.diseases[0];
  const pinCode = data.pinCode;
  console.log('Hospital ID is ' + context.params.hospitalId);

  const firestore = new Firestore();

//   var update = {
//   state: 'CA',
//   country: 'USA'
// };
  // var setDoc = firestore.collection('Hospitals').doc(context.params.hospitalId).set(update);

  var today = new Date();
  var dd = String(today.getDate());
  var mm = String(today.getMonth() + 1); //January is 0!
  var yyyy = today.getFullYear();

  var date = yyyy + '-' + mm + '-' + dd;

  var transaction = firestore.runTransaction(t => {
    return t.get(firestore.collection('Regions').doc(pinCode).collection('Diseases').doc(disease))
      .then(doc => {
        var newCurrent = doc.data().current + 1;
        t.update(firestore.collection('Regions').doc(pinCode).collection('Diseases').doc(disease), {
          [date]: newCurrent,
          current: newCurrent
        });
      }).then(result => {
        console.log('Transaction success!' );
      }).catch(err => {
        console.log('Transaction failure:', err);
      });
  });
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
