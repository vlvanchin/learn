alert('Welcome to Quiz Ninja');

const quiz = [
  ["What is superman's real name", "clark kent"],
  ["What is Wonder woman's real name", "Diana Prince"],
  ["what is batman's real name", "bruce wayne"]
];

let score = 0;

for (const [question, answer] of quiz) {
  const response = prompt(question);
  if (response === answer) {
    alert ("correct");
    score++
  } else {
    alert (`Wrong! the correct answer is ${answer}`);
  }
}

alert (`Game over, you scored ${score} point${score !== 1 ? 's' : ''}`);
