setTimeout(() => {
    throw new Error("Testing")
}, 300);

process.on("uncaughtException", () => {
    console.log("i run uncaughtException");
});

process.on("uncaughtExceptionMonitor", () => {
    console.log("i run uncaughtExceptionMonitor");
});

process.on("unhandledRejection", () => {
    console.log("i run unhandledRejection");
});