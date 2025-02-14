document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("customerForm");
    const submitBtn = document.getElementById("submitBtn");
    const messageEl = document.getElementById("message");

    form.addEventListener("submit", async function (event) {
        event.preventDefault(); // Prevent default form submission

        submitBtn.disabled = true;
        showMessage("Submitting...", "blue");

        // Get form data
        const customerData = {
            name: document.getElementById("name").value.trim(),
            address: document.getElementById("address").value.trim(),
            phone: document.getElementById("phone").value.trim(),
            additionalInfo: document.getElementById("additionalInfo").value.trim(),
            subscribe: document.getElementById("subscribe").checked
        };

        // Validation
        if (!customerData.name || !customerData.address || !customerData.phone) {
            showMessage("Please fill in all required fields.", "red");
            submitBtn.disabled = false;
            return;
        }

        // Simple phone validation (10-digit format)
        const phonePattern = /^\d{10}$/;
        if (!phonePattern.test(customerData.phone)) {
            showMessage("Enter a valid 10-digit phone number.", "red");
            submitBtn.disabled = false;
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/api/water2/save", { // Correct API path
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(customerData)
            });

            console.log("Response Status:", response.status);
            console.log("Response Headers:", [...response.headers]);

            const responseText = await response.text();
            console.log("Response Body:", responseText);

            if (!response.ok) {
                throw new Error(`Server error: ${response.status}, Details: ${responseText}`);
            }

            try {
                const jsonResponse = JSON.parse(responseText);
                showMessage("Customer added successfully!", "green");
            } catch {
                showMessage("Submission successful, but response is not JSON.", "orange");
            }

            // Reset the form after a short delay
            setTimeout(() => form.reset(), 500);
        } catch (error) {
            console.error("Fetch Error:", error);
            showMessage("Submission failed. Try again.", "red");
        }

        setTimeout(() => (submitBtn.disabled = false), 1000);
    });

    function showMessage(text, color) {
        messageEl.textContent = text;
        messageEl.style.color = color;
        messageEl.style.fontWeight = "bold";
    }
});
