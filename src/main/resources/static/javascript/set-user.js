axios.get('/users').then(resp => {
    let userHash;
    if (resp.data !== '') {
        console.log(resp.data.attributes.sub)
        this.$cookies.set("userHash", resp.data.attributes.sub)
    } else {
        console.log("anonymous")
        this.$cookies.set("userHash", "anonymous")
    }
})