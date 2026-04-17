const countries = [
  {
    id: 1,
    flag: "🇯🇵",
    name: "Japan",
    region: "East Asia",
    status: "Published",
    updatedAt: "Apr 17, 2026"
  },
  {
    id: 2,
    flag: "🇫🇷",
    name: "France",
    region: "Europe",
    status: "Draft",
    updatedAt: "Apr 16, 2026"
  },
  {
    id: 3,
    flag: "🇸🇬",
    name: "Singapore",
    region: "Southeast Asia",
    status: "Published",
    updatedAt: "Apr 15, 2026"
  },
  {
    id: 4,
    flag: "🇰🇷",
    name: "South Korea",
    region: "East Asia",
    status: "Review",
    updatedAt: "Apr 14, 2026"
  }
];

const activities = [
  {
    title: "Japan budget guide updated",
    description: "Mid-range daily budget and accommodation values were revised.",
    status: "Draft"
  },
  {
    title: "France culture guide edited",
    description: "Added etiquette notes and dining customs for first-time travelers.",
    status: "Saved"
  },
  {
    title: "Singapore checklist published",
    description: "New essentials and arrival-ready checklist are now live.",
    status: "Live"
  },
  {
    title: "South Korea entry requirement flagged",
    description: "Passport validity note needs verification before republishing.",
    status: "Review"
  }
];

document.addEventListener("DOMContentLoaded", () => {
  renderCountries(countries);
  renderActivities(activities);
  initNav();
  initSearch();
  initActions();
});

function renderCountries(items) {
  const tbody = document.getElementById("countryTableBody");
  if (!tbody) return;

  tbody.innerHTML = items.map(country => `
    <tr>
      <td>
        <div class="country-cell">
          <div class="flag-box">${country.flag}</div>
          <span>${country.name}</span>
        </div>
      </td>
      <td>${country.region}</td>
      <td>${renderStatusBadge(country.status)}</td>
      <td>${country.updatedAt}</td>
      <td>
        <div class="table-actions">
          <button class="mini-btn" onclick="handleView(${country.id})">View</button>
          <button class="mini-btn" onclick="handleEdit(${country.id})">Edit</button>
          <button class="mini-btn primary" onclick="handleManageContent(${country.id})">Manage Content</button>
        </div>
      </td>
    </tr>
  `).join("");
}

function renderActivities(items) {
  const container = document.getElementById("activityList");
  if (!container) return;

  container.innerHTML = items.map(activity => `
    <div class="list-item">
      <div>
        <h4>${activity.title}</h4>
        <p>${activity.description}</p>
      </div>
      ${renderStatusBadge(activity.status)}
    </div>
  `).join("");
}

function renderStatusBadge(status) {
  const normalized = status.toLowerCase();

  if (normalized === "published" || normalized === "live" || normalized === "saved" || normalized === "ready") {
    return `<span class="badge badge-success">${status}</span>`;
  }

  if (normalized === "draft") {
    return `<span class="badge badge-warning">${status}</span>`;
  }

  return `<span class="badge badge-danger">${status}</span>`;
}

function initNav() {
  const navItems = document.querySelectorAll(".nav-item");
  const pageTitle = document.getElementById("pageTitle");
  const pageSubtitle = document.getElementById("pageSubtitle");

  navItems.forEach(item => {
    item.addEventListener("click", e => {
      e.preventDefault();

      navItems.forEach(nav => nav.classList.remove("active"));
      item.classList.add("active");

      const section = item.dataset.section || "dashboard";

      pageTitle.textContent = formatSectionTitle(section);
      pageSubtitle.textContent = getSectionSubtitle(section);
    });
  });
}

function initSearch() {
  const input = document.getElementById("searchInput");
  if (!input) return;

  input.addEventListener("input", () => {
    const query = input.value.trim().toLowerCase();

    const filtered = countries.filter(country =>
      country.name.toLowerCase().includes(query) ||
      country.region.toLowerCase().includes(query) ||
      country.status.toLowerCase().includes(query)
    );

    renderCountries(filtered);
  });
}

function initActions() {
  const exportBtn = document.getElementById("exportBtn");
  const addCountryBtn = document.getElementById("addCountryBtn");

  if (exportBtn) {
    exportBtn.addEventListener("click", () => {
      alert("Export feature will be connected to backend later.");
    });
  }

  if (addCountryBtn) {
    addCountryBtn.addEventListener("click", () => {
      alert("Open add country modal/form here.");
    });
  }
}

function handleView(countryId) {
  alert(`View country ID: ${countryId}`);
}

function handleEdit(countryId) {
  alert(`Edit country ID: ${countryId}`);
}

function handleManageContent(countryId) {
  alert(`Manage content for country ID: ${countryId}`);
}

function formatSectionTitle(section) {
  switch (section) {
    case "dashboard": return "Editorial Command Center";
    case "analytics": return "Analytics Overview";
    case "users": return "User Management";
    case "countries": return "Country Management";
    case "requirements": return "Entry Requirements";
    case "budgets": return "Budget Guides";
    case "packing": return "Packing Checklists";
    case "tips": return "Local Tips";
    case "culture": return "Culture Guides";
    case "drafts": return "Draft Management";
    case "publish": return "Publish Queue";
    case "settings": return "System Settings";
    default: return "Wayfarer Admin";
  }
}

function getSectionSubtitle(section) {
  switch (section) {
    case "dashboard":
      return "Manage destinations, keep travel data accurate, and publish updates with confidence.";
    case "analytics":
      return "Track engagement, saves, and content performance across the platform.";
    case "users":
      return "Monitor user activity, saved destinations, and account-level insights.";
    case "countries":
      return "Create, update, publish, and archive country destination pages.";
    case "requirements":
      return "Maintain visa notes, passport rules, vaccinations, and customs guidance.";
    case "budgets":
      return "Update budget tiers, accommodation costs, transport, and saving tips.";
    case "packing":
      return "Manage packing categories, essentials, and arrival-ready checklists.";
    case "tips":
      return "Publish local travel advice, etiquette reminders, and practical guidance.";
    case "culture":
      return "Curate customs, do’s and don’ts, and country-specific cultural insights.";
    case "drafts":
      return "Review unpublished content and prepare updates before going live.";
    case "publish":
      return "Approve content changes and control what becomes visible to travelers.";
    case "settings":
      return "Manage admin preferences, permissions, and operational controls.";
    default:
      return "Admin tools for managing Wayfarer content.";
  }
}