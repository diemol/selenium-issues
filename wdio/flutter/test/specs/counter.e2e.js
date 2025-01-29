describe('Counter App - Shadow DOM', () => {
    it('Going into the Shadow DOM', async () => {
        await browser.url(`http://0.0.0.0:8080/`)
        await $('flt-glass-pane').waitForExist({timeout: 60000, interval: 5000})
        const semantics = await $('flt-glass-pane').shadow$('flt-semantics-placeholder')
        await browser.execute('arguments[0].click({force: true})', semantics)
        await $('flt-glass-pane').shadow$('[aria-label="0"]').waitForExist()
        await $('flt-glass-pane').shadow$('[aria-label="Increment"]').click()
        await $('flt-glass-pane').shadow$('[aria-label="1"]').waitForExist()
    })
})