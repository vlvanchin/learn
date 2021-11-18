function concatP (a, b) {
	return new Promise ( resolve => {
		concat(a,b, resolve)
	}, reject => {
		console.log('failed')
	})
}

function upperP (a) {
	return new Promise( resolve => {
		upper(a,resolve);
	}, reject => {
		console.log('failed')
	})
}

function decorP (a) {
	return new Promise (resolve => {
		decor(a, resolve);
	}, reject => {
		console.log('failed')
	})
}

concatP('hello','world')
	.then (upperP.bind(this))
	.then (decorP.bind(this))
	.then (console.log);


