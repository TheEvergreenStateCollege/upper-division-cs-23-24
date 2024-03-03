setTimeout(() => {
    throw new Error('Timeout error occured outside of express')
},300)

process.on('uncaughtException',() => {

})

process.on('unhandledRejection',() =>{

})